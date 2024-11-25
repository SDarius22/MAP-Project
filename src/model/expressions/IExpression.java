package model.expressions;

import exceptions.ExpressionException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.values.IValue;

public interface IExpression {
    IValue evaluate(MyIDictionary<String, IValue> symTbl, MyIHeap heap) throws ExpressionException;
    IExpression deepCopy();
}
