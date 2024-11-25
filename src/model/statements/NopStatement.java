package model.statements;

import model.state.ProgramState;

public class NopStatement implements IStatement {
    public NopStatement() {}

    @Override
    public ProgramState execute(ProgramState state) {
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NopStatement();
    }

    @Override
    public String toString() {
        return "NopStatement";
    }
}
