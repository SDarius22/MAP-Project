package repo;

import exceptions.RepoException;
import model.state.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    private List<ProgramState> states;
    private String logFilePath;
    private int currentProgramState = 0;

    public Repository(String logFilePath){
        this.states = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

    @Override
    public void addState(ProgramState state){
        this.states.add(state);
    }

    @Override
    public ProgramState getCurrentState() throws RepoException {
        if (this.states.isEmpty()){
            throw new RepoException("No program state available");
        }
        return this.states.get(currentProgramState);
    }

    public List<ProgramState> getInstances() {
        return states;
    }

    @Override
    public void logProgramState() throws RepoException {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            writer.println(this.getCurrentState());
            writer.close();
        }
        catch (IOException e){
            throw new RepoException(e.getMessage());
        }
    }


}
