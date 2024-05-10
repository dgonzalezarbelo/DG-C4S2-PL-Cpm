package ast.expressions.operands;

import java.util.List;

import ast.Josito;
import ast.expressions.Expression;
import ast.preamble.Method;
import exceptions.UndefinedFunctionException;
import exceptions.VisibilityException;

public class MethodCall extends Field {
    private FunctionCall func;

    public MethodCall(String opnd1, List<Expression> opnd2, int row) {
        super(opnd1, row);
        func = new FunctionCall(this.fieldname, opnd2, row);
    }

    public MethodCall(FunctionCall fc, int row) {
        super(fc.funcname, row);
        this.func = fc;
    }

    @Override
    public void bind() { 
        func.bind();
    }

    @Override
    public String toString() {return this.func.toString(); }

    @Override
    public void checkType() throws Exception {
        try {
            func.checkType();
        } catch (UndefinedFunctionException e) {
            // Nothing to do, there was no candidates as expected
        } catch (Exception e) {
            throw e;
        }
        
        try {
            Method matched = classFrom.hasMethod(func);
            func.matchingBind = matched;
            this.type = matched.getType();
        } catch (Exception e) {
            throw new VisibilityException(e.getMessage());
        }
    }

    @Override
    public void generateAddress(Josito jose) throws Exception { // Code_D
        func.setReference(1);
        this.func.generateAddress(jose);        // Nothing to do
    }

    
    @Override
    public void generateValue(Josito jose) throws Exception { // Code_E
        func.setReference(1);
        func.generateValue(jose);
    }
}

/**
 * class Alumno { // 8
 * 		private int notas;
 * 		public int notitas;
 * 
 * 		Alumno(int a, int b) {
 * 			notas = a;
 * 			dis.notitas = b;
 * 		}
 * 
 * 		public int calculaNotas() { // 4
 * 			notas = 3;
 * 			dis.notitas = 2;	
 * 		}
 * }
 * 
 * 
 * int main() {
 * 	int num = 0;
 * 	Alumno jose = Alumno(0, 1);
 * 	jose.notitas = 3;
 * 	
 * 
 * }
 */