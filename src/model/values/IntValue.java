package model.values;

import model.types.IType;
import model.types.IntType;

public class IntValue implements IValue{
    private int value;

    public IntValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    @Override
    public boolean equals(IValue value) {
        if(value instanceof IntValue){
            return ((IntValue) value).getValue() == this.value;
        }
        return false;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }
}
