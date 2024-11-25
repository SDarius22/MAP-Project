package model.statements;

import exceptions.ExpressionException;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.values.IValue;

public class PrintStatement implements IStatement {
    private IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ExpressionException {
        IValue val = expression.evaluate(state.getSymTable(), state.getHeap());
        state.getOutputList().add(val);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(this.expression.deepCopy());
    }

    @Override
    public String toString() {
        return "print(" + this.expression.toString() + ")";
    }
}
