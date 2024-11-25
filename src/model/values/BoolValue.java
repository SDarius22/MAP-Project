package model.values;

import model.types.BoolType;
import model.types.IType;

public class BoolValue implements IValue {
    private boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(IValue value) {
        if (value instanceof BoolValue) {
            return this.value == ((BoolValue) value).getValue();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
