package model.adt;

import java.util.List;

public interface MyIList<T> {
    void add(T element);
    List<T> getAll();
    String toString();
    void remove(T element);
}
