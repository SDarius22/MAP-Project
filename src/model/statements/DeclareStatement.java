package model.statements;

import exceptions.StatementException;
import model.state.ProgramState;
import model.types.IType;

public class DeclareStatement implements IStatement{
    private String name;
    private IType type;

    public DeclareStatement(String name, IType type) {
        this.name = name;
        this.type = type;
    }

    public ProgramState execute(ProgramState state) throws StatementException {
        if(state.getSymTable().contains(this.name)){
            throw new StatementException("A variable with the same name already exists.");
        }
        state.getSymTable().insert(this.name, this.type.getDefaultValue());
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new DeclareStatement(this.name, this.type);
    }

    @Override
    public String toString() {
        return this.type.toString() + " " + this.name;
    }
}
