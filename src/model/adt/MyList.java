package model.adt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyList<T> implements MyIList<T>{
    List<T> list;

    public MyList(){
        this.list = new ArrayList<>();
    }

    @Override
    public void add(T element) {

        this.list.add(element);

    }

    @Override
    public List<T> getAll() {
        return this.list;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        for(T el : this.list){
            str.append(el);
            str.append("\n");
        }

        return "Output list contains: " + str;

    }

    @Override
    public void remove(T element) {
        this.list.remove(element);
    }
}
