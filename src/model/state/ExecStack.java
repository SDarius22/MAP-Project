package model.state;

import exceptions.EmptyStackException;
import model.adt.MyStack;
import model.statements.IStatement;

public class ExecStack implements IExecStack {
    private MyStack<IStatement> stack;
    public ExecStack() {
        stack = new MyStack<>();
    }

    @Override
    public void push(IStatement statement) {
        this.stack.push(statement);
    }

    public IStatement pop() throws EmptyStackException {
        if (this.stack.isEmpty()){
            throw new EmptyStackException("Empty stack");
        }
        return this.stack.pop();
    }

    public int size(){
        return this.stack.size();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public MyStack<IStatement> getStack() {
        return this.stack;
    }
}