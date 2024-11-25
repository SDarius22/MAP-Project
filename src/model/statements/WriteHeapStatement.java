package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.types.RefType;
import model.values.IValue;
import model.values.RefValue;

public class WriteHeapStatement implements IStatement{
    private String variableName;
    private IExpression expression;

    public WriteHeapStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        if (!state.getSymTable().contains(this.variableName)) {
            throw new StatementException("Variable '" + this.variableName + "' not defined");
        }

        if (!(state.getSymTable().get(this.variableName).getType() instanceof RefType)) {
            throw new StatementException("The variable is not a RefType");
        }

        IValue value = state.getSymTable().get(this.variableName);
        if (!(value instanceof RefValue refValue)) {
            throw new StatementException("The value is not a RefValue");
        }

        int address = refValue.getAddress();
        if (!state.getHeap().exists(address)) {
            throw new StatementException("The address is not in the heap");
        }

        IValue expressionValue = this.expression.evaluate(state.getSymTable(), state.getHeap());
        state.getHeap().set(address, expressionValue);

        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new WriteHeapStatement(this.variableName, this.expression.deepCopy());
    }

    @Override
    public String toString() {
        return "writeHeap(" + this.variableName + ", " + this.expression.toString() + ")";
    }
}
