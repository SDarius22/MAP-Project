package repo;

import model.state.ProgramState;
import model.statements.IStatement;

import java.util.List;

public interface IRepository {
    void addState(ProgramState state);
    //ProgramState getCurrentState();
    void logProgramState(ProgramState state);
    List<ProgramState> getPrgList();
    void setPrgList(List<ProgramState> prgList);
}
