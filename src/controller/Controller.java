package controller;

import exceptions.ControllerException;
import exceptions.ExpressionException;
import model.adt.MyIHeap;
import model.adt.MyIStack;
import model.state.ProgramState;
import model.statements.IStatement;
import model.values.IValue;
import model.values.RefValue;
import repo.IRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class Controller {
    public IRepository repo;
    private ExecutorService exec;

    public Controller(IRepository repo, ExecutorService exec) {
        this.exec = exec;
        this.repo = repo;
    }

    public void addState(ProgramState state) {
        repo.addState(state);
    }

    public Map<Integer, IValue> safeGarbageCollector(List<Integer> activeAddresses, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> activeAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    public List<Integer> getAllActiveAddresses(Collection<IValue> symTableValues, MyIHeap heap) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> (RefValue) v)
                .flatMap(
                        v -> {
                            List<Integer> addresses = new ArrayList<>();
                            while (true) {
                                if (v.getAddress() == 0) {
                                    break;
                                }
                                addresses.add(v.getAddress());
                                try {
                                    IValue nextVal = heap.get(v.getAddress());
                                    if (nextVal instanceof RefValue) {
                                        v = (RefValue) nextVal;
                                    } else {
                                        break;
                                    }
                                } catch (ExpressionException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            return addresses.stream();
                        }
                )
                .collect(Collectors.toList());
    }


    public ProgramState oneStep(ProgramState state) throws ControllerException {
        try {
            MyIStack<IStatement> stack = state.getExecStack();
            IStatement currentStatement = stack.pop();
            return currentStatement.execute(state);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    public void allSteps() throws ControllerException {
        try {
            ProgramState state = repo.getCurrentState();
            this.repo.logProgramState();
            while (!state.getExecStack().isEmpty()) {
                oneStep(state);
                state.getHeap().setContent(safeGarbageCollector(getAllActiveAddresses(state.getSymTable().getValues(), state.getHeap()), state.getHeap().getContent()));
                this.repo.logProgramState();
            }
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    public List<ProgramState> removeCompletedPrg(List<ProgramState> states) {
        return states.stream()
                .filter(v -> v.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAll(List<ProgramState> states) throws InterruptedException {
        states.stream()
                .forEach(c -> repo.logProgramState(c));
        List<Callable<ProgramState>> callables = states.stream()
                .map(p -> ((Callable<ProgramState>) () -> p.oneStep()))
                .toList();
        List<ProgramState> newprg = exec.invokeAll(callables).stream().map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        ).filter(p -> p != null).toList();
        states.addAll(newprg);
        states.stream()
                .forEach(c -> repo.logProgramState(c));
        repo.setPrgList(newprg);

    }
}
