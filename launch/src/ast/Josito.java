package ast;

import java.util.StringJoiner;

import ast.expressions.Expression.Operator_T;

public class Josito {
    private static StringJoiner code = new StringJoiner("\n");
    // Just
    // Other
    // Sintax
    // Interpreter
    // Translator
    // Output
    
    private int indentation;
    private int markPointer;    // points
    private int stackPointer;   // points to the last occupied position of the memory 
    private int newPointer;     // points to the last occupied position of the heap

    public void programHeader() {
        code.add("(memory 2000)");
        code.add("(global $SP (mut i32) (i32.const 0)) ;; start of stack");
        code.add("(global $MP (mut i32) (i32.const 0)) ;; mark pointer");
        code.add("(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4");
        code.add("(func $reserveStack (param $size i32)");
        code.add("(result i32)");
        code.add("get_global $MP");
        code.add("get_global $SP");
        code.add("set_global $MP");
        code.add("get_global $SP");
        code.add("get_local $size");
        code.add("i32.add");
        code.add("set_global $SP");
        code.add("get_global $SP");
        code.add("get_global $NP");
        code.add("i32.gt_u");
        code.add("if");
        code.add("i32.const 3");
        code.add("call $exception");
        code.add("end");
        code.add(")");
    }

    public void funcHeader(String name) {
        append(indentate("(func $%s"), name);
        indentation++;
    }

    public void funcTail() {
        indentation--;
        append(indentate(")"));
    }

    public void addArgument() {
        
    }

    public void createConst(int val) { //TODO dani, esto no tiene indentate
        append("i32.const %d", val);
    }

    public void createIdentifier(int delta) { //TODO dani, esto no tiene indentate
        append("i32.const %d", delta);
        append("i32.const %d", markPointer); // TODO obtener inicio del marco de id
        append("i32.add");
    }

    
    // AND, DIV, EQ, FIELD_ACCESS, GREATER, GEQ, LESS, LEQ, MINUS, MOD, MULT, NEQ, NOT, OR, PTR, POW, REFERENCE, SQ_BRACKET, SUB, ADD }

    public void translateOperator(Operator_T operator) {
        switch (operator) {
            case Operator_T.AND:
                break;
            case Operator_T.DIV:
                break;
            case Operator_T.EQ:
                break;
            case Operator_T.FIELD_ACCESS:
                break;
            case Operator_T.GREATER:
                break;
            case Operator_T.GEQ:
                break;
            case Operator_T.LESS:
                break;
            case Operator_T.LEQ:
                break;
            case Operator_T.MINUS:
                break;
            case Operator_T.MOD:
                break;
            case Operator_T.MULT:
                break;
            case Operator_T.NEQ:
                break;
            case Operator_T.NOT:
                break;
            case Operator_T.OR:
                break;
            case Operator_T.PTR:
                break;
            case Operator_T.POW:
                break;
            case Operator_T.REFERENCE:
                break;
            case Operator_T.SQ_BRACKET:
                break;
            case Operator_T.SUB:
                break;
            case Operator_T.ADD:
                break;
        }
    }

    public String toString() {
        return code.toString();
    }
    
    private String indentate(String instruction) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentation; i++)
            sb.append("    ");
        sb.append(instruction);
        return sb.toString();
    }

    private void append(String instruction) {
        code.add(indentate(instruction));
    }

    private void append(String instruction, Object... args) {
        append(String.format(instruction, args));
    }
}
