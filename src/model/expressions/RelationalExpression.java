package model.expressions;

import exceptions.ExpressionException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

public class RelationalExpression implements IExpression{
    private IExpression left;
    private IExpression right;
    private RelationalOperation operation;

    public RelationalExpression(IExpression left, RelationalOperation operation, IExpression right) {
        this.left = left;
        this.operation = operation;
        this.right = right;
    }

    public IValue evaluate(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws ExpressionException {
        IValue left = this.left.evaluate(symTable, heap);
        IValue right = this.right.evaluate(symTable, heap);

        if(!(left.getType().equals(new IntType()) && right.getType().equals(new IntType()))){
            throw new ExpressionException("The values are not integers!");
        }
        int leftValue = ((IntValue)(left)).getValue();
        int rightValue = ((IntValue)(right)).getValue();

        switch(operation){
            case EQUAL:
                return new BoolValue(leftValue == rightValue);
            case NOT_EQUAL:
                return new BoolValue(leftValue != rightValue);
            case LESS:
                return new BoolValue(leftValue < rightValue);
            case LESS_EQUAL:
                return new BoolValue(leftValue <= rightValue);
            case GREATER:
                return new BoolValue(leftValue > rightValue);
            case GREATER_EQUAL:
                return new BoolValue(leftValue >= rightValue);
            default:
                throw new ExpressionException("Invalid operation!");

        }

    }

    @Override
    public IExpression deepCopy() {
        return new RelationalExpression(left.deepCopy(), operation, right.deepCopy());
    }

    @Override
    public String toString(){
        String opString = "";
        switch(operation){
            case EQUAL:
                opString = "==";
                break;
            case NOT_EQUAL:
                opString = "!=";
                break;
            case LESS:
                opString = "<";
                break;
            case LESS_EQUAL:
                opString = "<=";
                break;
            case GREATER:
                opString = ">";
                break;
            case GREATER_EQUAL:
                opString = ">=";
                break;
        }
        return left.toString() + " " + opString + " " + right.toString();
    }
}
