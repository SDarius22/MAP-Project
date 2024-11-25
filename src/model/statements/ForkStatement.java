package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.state.ProgramState;

public class ForkStatement implements IStatement{

    private IStatement statement;

    public ForkStatement(IStatement statement){
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {

        ProgramState newProgramState = new ProgramState(this.statement,state.getExecStack(),
                state.getSymTable().deepCopy(),state.getOutputList(),state.getFileTable(),state.getHeap() );

        return newProgramState;
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(statement.deepCopy());
    }

    @Override
    public String toString() {
        return "fork("+statement+")";
    }
}
