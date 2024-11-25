package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.types.IType;
import model.types.RefType;
import model.values.IValue;
import model.values.RefValue;

public class AllocationStatement implements IStatement {
    private String variableName;
    private IExpression expression;

    public AllocationStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    public String getVariableName() {
        return variableName;
    }

    public IExpression getExpression() {
        return expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        var symTable = state.getSymTable();
        var heap = state.getHeap();
        if(!symTable.contains(variableName)){
            throw new StatementException("There is no variable " + variableName + " in the sym table");
        }

        IValue variableValue = symTable.get(this.variableName);

        if(!(variableValue.getType() instanceof RefType)){
            throw new StatementException("Variable is not of Ref type");
        }

        IValue value = expression.evaluate(symTable, heap);

        if(!value.getType().equals(((RefType)variableValue.getType()).getInner())){
            throw new StatementException("The type of the expression does not match the inner type of the variable");
        }

        int address = heap.allocate(value);

        symTable.insert(variableName,new RefValue(address,value.getType()));
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new AllocationStatement(this.variableName,this.expression);
    }

    @Override
    public String toString() {
        return "new(" + variableName + ", " + expression.toString() + ")";
    }


}
