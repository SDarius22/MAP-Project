package model.adt;

import exceptions.ExpressionException;

import java.util.*;

public class MyDictionary <K, V> implements MyIDictionary<K, V> {
    Map<K, V> map;

    public MyDictionary() {
        map = new HashMap<>();
    }

    public MyDictionary(Map<K, V> map) {
        this.map = map;
    }

    @Override
    public void insert(K key, V value) {
        this.map.put(key, value);
    }

    @Override
    public void remove(K key) throws ExpressionException {
        if(this.map.containsKey(key))
            this.map.remove(key);
        else
            throw new ExpressionException("Key not found");
    }

    @Override
    public boolean contains(K key) {
        return this.map.containsKey(key);
    }

    @Override
    public V get(K key) throws ExpressionException {
        if(this.map.containsKey(key)){
            return this.map.get(key);
        }
        else {
            throw new ExpressionException("Key not found");
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        this.map.forEach((k,v)->{
            str.append(k).append(" -> ").append(v).append("\n");
        });
        return "Dictionary contains: " + str;

    }

    @Override
    public Set<K> getKeys() {
        return this.map.keySet();
    }

    @Override
    public ArrayList<V> getValues() {
        return new ArrayList<>(this.map.values());
    }

    public void clear(){
        this.map.clear();
    }

    @Override
    public MyIDictionary<K, V> deepCopy() {
        return new MyDictionary<>(this.map);
    }
}
