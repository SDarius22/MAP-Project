package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;

public class OpenFileStatement implements IStatement {
    private IExpression expression;

    public OpenFileStatement(IExpression exp) {
        this.expression = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        IValue eval = this.expression.evaluate(state.getSymTable(), state.getHeap());
        if (!eval.getType().equals(new StringType())) {
            throw new StatementException("The expression does not evaluate to a string type");
        }
        if (state.getFileTable().contains((StringValue) eval)) {
            throw new StatementException("The file already exists");
        }
        try{
            BufferedReader reader = new BufferedReader(new FileReader(((StringValue) eval).getValue()));
            state.getFileTable().insert((StringValue) eval, reader);
        }
        catch (Exception e){
            throw new StatementException("Error opening file");
        }
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new OpenFileStatement(this.expression);
    }

    @Override
    public String toString() {
        return "openFile(" + this.expression.toString() + ")";
    }
}
