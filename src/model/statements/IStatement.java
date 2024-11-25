package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.state.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws StatementException, ExpressionException;
    IStatement deepCopy();
}
