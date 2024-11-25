package model.adt;

import exceptions.ExpressionException;
import model.values.IValue;

import java.util.HashMap;
import java.util.Map;

public class MyHeap implements MyIHeap {
    private MyIDictionary<Integer, IValue> map;
    private Integer freeLocation;

    public MyHeap() {
        map = new MyDictionary<>();
        freeLocation = 1;
    }

    @Override
    public Integer allocate(IValue name) {
        map.insert(freeLocation++, name);
        return freeLocation - 1;
    }

    @Override
    public IValue get(Integer address) throws ExpressionException {
        return map.get(address);
    }

    @Override
    public boolean exists(Integer address) {
        return map.contains(address);
    }

    @Override
    public Integer getFreeLocation() {
        return freeLocation;
    }

    @Override
    public void set(Integer address, IValue value) {
        map.insert(address, value);
    }

    @Override
    public Map<Integer, IValue> getContent() {
        Map<Integer, IValue> heap = new HashMap<>();
        map.getKeys().forEach(key -> {
            try {
                heap.put(key, map.get(key));
            } catch (ExpressionException e) {
                e.printStackTrace();
            }
        });
        return heap;
    }

    @Override
    public void setContent(Map<Integer, IValue> heap) {
        map = new MyDictionary<>();
        heap.forEach((k, v) -> map.insert(k, v));
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Heap:\n");
        map.getKeys().forEach(key -> {
            try {
                builder.append(key.toString() + "->" + map.get(key).toString() + "\n");
            } catch (ExpressionException e) {
                builder.append("");
            }
        });
        return builder.toString();
    }
}
