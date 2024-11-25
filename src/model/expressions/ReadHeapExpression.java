package model.expressions;


import exceptions.ExpressionException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.values.IValue;
import model.values.RefValue;

public class ReadHeapExpression implements IExpression {
    private IExpression expression;

    public ReadHeapExpression(IExpression expression) {
        this.expression = expression;
    }

    public IExpression getExpression() {
        return expression;
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTbl, MyIHeap heap) throws ExpressionException {
        IValue value = expression.evaluate(symTbl, heap);
        if (!(value instanceof RefValue refValue)) {
            throw new ExpressionException("The expression is not a RefValue");
        }
        int address = refValue.getAddress();
        if (!heap.exists(address)) {
            throw new ExpressionException("The address is not in the heap");
        }
        return heap.get(address);
    }

    @Override
    public IExpression deepCopy() {
        return new ReadHeapExpression(this.expression.deepCopy());
    }

    @Override
    public String toString() {
        return "readHeap(" + this.expression.toString() + ")";
    }
}
