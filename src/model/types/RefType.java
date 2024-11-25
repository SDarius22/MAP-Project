package model.types;

import model.values.IValue;
import model.values.RefValue;

public class RefType implements IType{
    private IType inner;

    public RefType(IType inner) {
        this.inner = inner;
    }

    public IType getInner() {
        return inner;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RefType){
            return inner.equals(((RefType) obj).getInner());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Ref " + inner.toString();
    }

    @Override
    public boolean equals(IType type) {
        return type instanceof RefType && ((RefType) type).getInner().equals(this.inner);
    }

    @Override
    public IValue getDefaultValue() {
        return new RefValue(0, inner);
    }
}
