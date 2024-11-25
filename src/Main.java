import controller.Controller;
import model.adt.*;
import model.expressions.*;
import model.state.ProgramState;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.RefType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;
import repo.IRepository;
import repo.Repository;
import view.TextMenu;
import view.commands.ExitCommand;
import view.commands.RunExampleCommand;

import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {
        IStatement ex1= new CompoundStatement(
                new DeclareStatement("x",new IntType()),
                new CompoundStatement(
                        new AssignStatement("x",new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("x"))
                )
        );
        MyIStack<IStatement> stack1 = new MyStack<>();
        MyIDictionary<String, IValue> symTable1 = new MyDictionary<>();
        MyIList<IValue> out1 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();
        MyIHeap heap1 = new MyHeap();
        ProgramState prg1 = new ProgramState(ex1, stack1, symTable1, out1, fileTable1, heap1);

        IRepository repo1 = new Repository("log1.txt");
        repo1.addState(prg1);
        Controller ctrl1 = new Controller(repo1);


        IStatement ex2 = new CompoundStatement(
                new DeclareStatement("a",new IntType()),
                new CompoundStatement(
                        new DeclareStatement("b",new IntType()),
                        new CompoundStatement(
                                new AssignStatement("a",
                                        new ArithmeticalExpression(
                                                new ValueExpression(new IntValue(2)),
                                                new ArithmeticalExpression(
                                                        new ValueExpression(new IntValue(3)),
                                                        new ValueExpression(new IntValue(5)),
                                                        ArithmeticalOperation.MULTIPLY),
                                                ArithmeticalOperation.PLUS)
                                ),
                                new CompoundStatement(
                                        new AssignStatement("b",
                                                new ArithmeticalExpression(
                                                        new VariableExpression("a"),
                                                        new ValueExpression(new IntValue(1)),
                                                        ArithmeticalOperation.PLUS)
                                        ),
                                        new PrintStatement(new VariableExpression("b"))
                                )
                        )
                )
        );
        MyIStack<IStatement> stack2 = new MyStack<>();
        MyIDictionary<String, IValue> symTable2 = new MyDictionary<>();
        MyIList<IValue> out2 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable2 = new MyDictionary<>();
        MyIHeap heap2 = new MyHeap();
        ProgramState prg2 = new ProgramState(ex2, stack2, symTable2, out2, fileTable2, heap2);

        IRepository repo2 = new Repository("log2.txt");
        repo2.addState(prg2);
        Controller ctrl2 = new Controller(repo2);


        IStatement ex3 = new CompoundStatement(
                new DeclareStatement("a",new BoolType()),
                new CompoundStatement(
                        new DeclareStatement("v",new IntType()),
                        new CompoundStatement(
                                new AssignStatement("a",new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(
                                        new IfStatement(
                                                new VariableExpression("a"),
                                                new AssignStatement("v",new ValueExpression(new IntValue(2))),
                                                new AssignStatement("v",new ValueExpression(new IntValue(3))
                                                )
                                        ),
                                        new PrintStatement(new VariableExpression("v"))
                                )
                        )
                )
        );

        MyIStack<IStatement> stack3 = new MyStack<>();
        MyIDictionary<String, IValue> symTable3 = new MyDictionary<>();
        MyIList<IValue> out3 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable3 = new MyDictionary<>();
        MyIHeap heap3 = new MyHeap();
        ProgramState prg3 = new ProgramState(ex3, stack3, symTable3, out3, fileTable3, heap3);

        IRepository repo3 = new Repository("log3.txt");
        repo3.addState(prg3);
        Controller ctrl3 = new Controller(repo3);

        IStatement ex4 = new CompoundStatement(
                new DeclareStatement("varf",new StringType()),
                new CompoundStatement(
                        new AssignStatement("varf",new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(
                                new OpenFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(
                                        new DeclareStatement("varc",new IntType()),
                                        new CompoundStatement(
                                                new ReadFileStatement(new VariableExpression("varf"),"varc"),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(
                                                                new ReadFileStatement(new VariableExpression("varf"),"varc"),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseFileStatement(new VariableExpression("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        MyIStack<IStatement> stack4 = new MyStack<>();
        MyIDictionary<String, IValue> symTable4 = new MyDictionary<>();
        MyIList<IValue> out4 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable4 = new MyDictionary<>();
        MyIHeap heap4 = new MyHeap();
        ProgramState prg4 = new ProgramState(ex4, stack4, symTable4, out4, fileTable4, heap4);

        IRepository repo4 = new Repository("log4.txt");
        repo4.addState(prg4);
        Controller ctrl4 = new Controller(repo4);


        // Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStatement ex5 = new CompoundStatement(
                new DeclareStatement("v", new RefType(new IntType())),
                new CompoundStatement(
                        new AllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new DeclareStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(
                                        new AllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new AllocationStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(
                                                        new ReadHeapExpression(
                                                                new ReadHeapExpression(
                                                                        new VariableExpression("a")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        MyIStack<IStatement> stack5 = new MyStack<>();
        MyIDictionary<String, IValue> symTable5 = new MyDictionary<>();
        MyIList<IValue> out5 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable5 = new MyDictionary<>();
        MyIHeap heap5 = new MyHeap();
        ProgramState prg5 = new ProgramState(ex5, stack5, symTable5, out5, fileTable5, heap5);

        IRepository repo5 = new Repository("log5.txt");
        repo5.addState(prg5);
        Controller ctrl5 = new Controller(repo5);

        IStatement ex6 = new CompoundStatement(
                new DeclareStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(
                                new WhileStatement(
                                        new RelationalExpression(
                                                new VariableExpression("v"),
                                                RelationalOperation.GREATER,
                                                new ValueExpression(new IntValue(0))
                                        ),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssignStatement("v",
                                                        new ArithmeticalExpression(
                                                                new VariableExpression("v"),
                                                                new ValueExpression(new IntValue(1)),
                                                                ArithmeticalOperation.MINUS
                                                        )
                                                )
                                        )
                                ),
                                new PrintStatement(new VariableExpression("v"))
                        )
                )
        );


        MyIStack<IStatement> stack6 = new MyStack<>();
        MyIDictionary<String, IValue> symTable6 = new MyDictionary<>();
        MyIList<IValue> out6 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable6 = new MyDictionary<>();
        MyIHeap heap6 = new MyHeap();
        ProgramState prg6 = new ProgramState(ex6, stack6, symTable6, out6, fileTable6, heap6);

        IRepository repo6 = new Repository("log6.txt");
        repo6.addState(prg6);
        Controller ctrl6 = new Controller(repo6);


        IStatement ex7 = new CompoundStatement(
                new DeclareStatement("v", new RefType(new IntType())),
                new CompoundStatement(
                        new AllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new AllocationStatement("v", new ValueExpression(new IntValue(30))),
                                new AllocationStatement("v", new ValueExpression(new IntValue(40)))
                        )
                )
        );

        MyIStack<IStatement> stack7 = new MyStack<>();
        MyIDictionary<String, IValue> symTable7 = new MyDictionary<>();
        MyIList<IValue> out7 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable7 = new MyDictionary<>();
        MyIHeap heap7 = new MyHeap();
        ProgramState prg7 = new ProgramState(ex7, stack7, symTable7, out7, fileTable7, heap7);

        IRepository repo7 = new Repository("log7.txt");
        repo7.addState(prg7);
        Controller ctrl7 = new Controller(repo7);



        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", ex1.toString(), ctrl1));
        menu.addCommand(new RunExampleCommand("2", ex2.toString(), ctrl2));
        menu.addCommand(new RunExampleCommand("3", ex3.toString(), ctrl3));
        menu.addCommand(new RunExampleCommand("4", ex4.toString(), ctrl4));
        menu.addCommand(new RunExampleCommand("5", ex5.toString(), ctrl5));
        menu.addCommand(new RunExampleCommand("6", ex6.toString(), ctrl6));
        menu.addCommand(new RunExampleCommand("7", ex7.toString(), ctrl7));
        menu.show();
    }
}