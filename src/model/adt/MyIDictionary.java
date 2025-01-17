package model.adt;

import exceptions.ExpressionException;

import java.util.List;
import java.util.Set;

public interface MyIDictionary<K, V> {
    void insert(K key, V value);
    void remove(K key) throws ExpressionException;
    boolean contains(K key);
    public V get(K key) throws ExpressionException;
    String toString();
    Set<K> getKeys();
    List<V> getValues();
    void clear();
    MyIDictionary<K, V> deepCopy();
}
