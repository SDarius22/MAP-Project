package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;

public class IfStatement implements IStatement{
    private IExpression condition;
    private IStatement thenStatement;
    private IStatement elseStatement;

    public IfStatement(IExpression condition, IStatement thenStatement, IStatement elseStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        IValue val;
        try {
            val = condition.evaluate(state.getSymTable(), state.getHeap());
        } catch (ExpressionException e) {
            throw new ExpressionException("The condition is not a valid expression.");
        }
        if(!val.getType().equals(new BoolType())){
            throw new StatementException("The condition is not a boolean.");
        }
        if(((BoolValue)val).getValue()) {
            state.getExecStack().push(thenStatement);
        }
        else{
            state.getExecStack().push(elseStatement);
        }
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(condition.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }

    public String toString() {
        return "IF(" + condition.toString() + ") THEN{" + thenStatement.toString() + "}ELSE{" + elseStatement.toString() + "}";
    }
}
