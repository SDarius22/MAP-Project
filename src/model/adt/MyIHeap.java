package model.adt;

import exceptions.ExpressionException;
import model.values.IValue;

import java.util.Map;

public interface MyIHeap {
    public Integer allocate(IValue name);

    public IValue get(Integer address) throws ExpressionException;

    public boolean exists(Integer address);

    public Integer getFreeLocation();

    public void set(Integer address, IValue value);

    public Map<Integer, IValue> getContent();

    public void setContent(Map<Integer, IValue> heap);
}
