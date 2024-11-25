package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.types.BoolType;
import model.values.BoolValue;

public class WhileStatement implements IStatement{
    private IExpression expression;
    private IStatement statement;

    public WhileStatement(IExpression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        if (expression.evaluate(state.getSymTable(), state.getHeap()).getType().equals(new BoolType())) {
            if (expression.evaluate(state.getSymTable(), state.getHeap()).equals(new BoolValue(true))) {
                state.getExecStack().push(this);
                state.getExecStack().push(statement);
            }
        } else {
            throw new StatementException("Expression is not a boolean");
        }
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(expression.deepCopy(), statement.deepCopy());
    }

    @Override
    public String toString() {
        return "(while (" + expression.toString() + ") " + statement.toString() + " )";
    }
}
