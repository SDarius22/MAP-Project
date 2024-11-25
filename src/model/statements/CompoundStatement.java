package model.statements;

import model.state.ProgramState;

public class CompoundStatement implements IStatement {
    private IStatement first;
    private IStatement second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        state.getExecStack().push(second);
        state.getExecStack().push(first);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new CompoundStatement(first.deepCopy(), second.deepCopy());
    }

    public String toString() {
        return first.toString() + "; " + second.toString();
    }
}