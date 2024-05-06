package ast;
 
import ast.sentences.instructions.Instruction;

public class Error_Ins extends Instruction {
    
    public Error_Ins(int row) {
        super(null, null, row);
    }
   
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("ERROR INS\n");
        return str.toString();
    }

    @Override
    public void bind() {
        // Nothing to do
    }

    @Override
    public void checkType() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkType'");
    }
}