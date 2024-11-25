package model.state;

import exceptions.ControllerException;
import model.adt.*;
import model.statements.IStatement;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;

public class ProgramState {
    private MyIStack<IStatement> execStack;
    private MyIDictionary<String, IValue> symTable;
    private MyIList<IValue> outputList;
    private IStatement originalStatement;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private MyIHeap heap;
    static int nextId = 0;
    private int id;

    public ProgramState(IStatement initState, MyIStack<IStatement> execStack, MyIDictionary<String, IValue> symTable, MyIList<IValue> outputList, MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap heap) {
        this.execStack = execStack;
        this.symTable = symTable;
        this.outputList = outputList;
        this.fileTable = fileTable;
        this.originalStatement = initState.deepCopy();
        this.heap = heap;
        this.execStack.push(initState);
        id = this.getNextId();
    }

    public MyIStack<IStatement> getExecStack() {
        return execStack;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }


    public MyIDictionary<String, IValue> getSymTable() {
        return symTable;
    }

    public MyIList<IValue> getOutputList() {
        return outputList;
    }

    public MyIHeap getHeap() {
        return heap;
    }

    @Override
    public String toString() {
        return "Current prg state with id: " + id + execStack.toString() + "\n" + symTable.toString() + "\n" + outputList.toString() + "\n" + this.toFile() + "\n" + heap.toString();
    }

    public String toFile() {
        String result = "File Table:\n";
        try{
            for (StringValue key : fileTable.getKeys()) {
                result += key.getValue() + "\n";
            }
        } catch (Exception e) {
            result += "File table is empty\n";
        }
        return result;
    }

    public boolean isNotCompleted() {
        return !execStack.isEmpty();
    }

    public synchronized int getNextId() {
        return ++nextId;
    }
    public ProgramState oneStep() throws ControllerException {
        try {
            MyIStack<IStatement> stack = this.getExecStack();
            IStatement currentStatement = stack.pop();
            return currentStatement.execute(this);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }
}
