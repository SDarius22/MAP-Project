package model.expressions;

import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.values.IValue;

public class ValueExpression implements IExpression {
    private IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTbl, MyIHeap heap) {
        return this.value;
    }

    @Override
    public IExpression deepCopy() {
        return new ValueExpression(this.value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
