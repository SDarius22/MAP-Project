package model.types;

import model.values.IValue;
import model.values.StringValue;

public class StringType implements IType {
    public boolean equals(IType type) {
        return type instanceof StringType;
    }

    @Override
    public IValue getDefaultValue() {
        return new StringValue("");
    }

    public String toString() {
        return "string";
    }
}
