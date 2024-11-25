package model.expressions;

import exceptions.ExpressionException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;

public class LogicalExpression implements IExpression{
    private IExpression left;
    private IExpression right;
    private LogicalOperation operation;

    public LogicalExpression(IExpression left, LogicalOperation operation, IExpression right) {
        this.left = left;
        this.operation = operation;
        this.right = right;
    }

    public IValue evaluate(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws ExpressionException {
        IValue left = this.left.evaluate(symTable, heap);
        IValue right = this.right.evaluate(symTable, heap);

        if(!(left.getType().equals(new BoolType()) && right.getType().equals(new BoolType()))){
            throw new ExpressionException("The values are not boolean!");
        }
        Boolean leftValue = ((BoolValue)(left)).getValue();
        Boolean rightValue = ((BoolValue)(right)).getValue();
        if(operation == LogicalOperation.AND){
            return new BoolValue(leftValue && rightValue);
        }
        else {
            return new BoolValue(leftValue || rightValue);
        }

    }

    @Override
    public IExpression deepCopy() {
        return new LogicalExpression(left.deepCopy(), operation, right.deepCopy());
    }

    @Override
    public String toString(){
        return left.toString() + " " + operation.toString().toLowerCase() + " " + right.toString();
    }
}
