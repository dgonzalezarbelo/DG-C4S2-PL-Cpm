package ast;
 
import ast.sentences.instructions.Instruction;

public class Error_Ins extends Instruction {
    
    public Error_Ins() {
        super(null, null);
    }

    @Override
    public KindE kind() {
       throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("ERROR\n");
        return str.toString();
    }
}
