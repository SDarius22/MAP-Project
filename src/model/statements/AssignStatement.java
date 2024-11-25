package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.values.IValue;

public class AssignStatement implements IStatement {
    private String id;
    private IExpression expression;

    public AssignStatement(String id, IExpression expression) {
        this.id = id;
        this.expression = expression;
    }

    public String toString(){
        return id + "=" + expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        if(!state.getSymTable().contains(id)){
            throw new StatementException("The variable " + id +  " was not declared previously.");
        }
        IValue val = expression.evaluate(state.getSymTable(), state.getHeap());
        if(!val.getType().equals(state.getSymTable().get(id).getType())){
            throw new StatementException("The types of the 2 variables do not match.");
        }
        state.getSymTable().insert(id,val);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignStatement(id, expression.deepCopy());
    }

}
