package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

public class CloseFileStatement implements IStatement{
    private IExpression expression;

    public CloseFileStatement(IExpression exp) {
        this.expression = exp;
    }


    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        IValue eval = this.expression.evaluate(state.getSymTable(), state.getHeap());
        if (!eval.getType().equals(new StringType())) {
            throw new StatementException("The expression does not evaluate to a string type");
        }
        if (!state.getFileTable().contains((StringValue) eval)) {
            throw new StatementException("The file does not exist");
        }
        try {
            state.getFileTable().get((StringValue) eval).close();
        }
        catch (Exception e) {
            throw new StatementException("Error closing file");
        }
        state.getFileTable().remove((StringValue) eval);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new CloseFileStatement(this.expression.deepCopy());
    }

    @Override
    public String toString() {
        return "closeFile(" + this.expression.toString() + ")";
    }
}
