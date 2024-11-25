package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.ProgramState;
import model.types.IntType;
import model.types.StringType;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement {
    private IExpression expression;
    private String variableName;

    public ReadFileStatement(IExpression exp, String var_name) {
        this.expression = exp;
        this.variableName = var_name;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        if (!state.getSymTable().contains(this.variableName)) {
            throw new StatementException("Variable '" + this.variableName + "' not defined");
        }

        if (!state.getSymTable().get(this.variableName).getType().equals(new IntType()) ) {
            throw new StatementException("The variable is not an integer type");
        }

        IValue eval = this.expression.evaluate(state.getSymTable(), state.getHeap());

        if (!eval.getType().equals(new StringType())){

            throw new ExpressionException("The expression does not evaluate to a string type");
        }

        if (!state.getFileTable().contains(((StringValue) eval))){
            throw new StatementException("The file does not exist");
        }

        BufferedReader reader = state.getFileTable().get(((StringValue) eval));

        try {
            String fileLine = reader.readLine();
            if (fileLine == null) {
                fileLine = "0";
            }
            int intFileLine = Integer.parseInt(fileLine);
            state.getSymTable().insert(this.variableName, new IntValue(intFileLine));
        }
        catch (IOException e) {
            throw new StatementException("Error reading file");
        }
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFileStatement(expression.deepCopy(), variableName);
    }

    @Override
    public String toString() {
        return "readFile(" + this.expression.toString() + ", " + this.variableName + ")";
    }
}