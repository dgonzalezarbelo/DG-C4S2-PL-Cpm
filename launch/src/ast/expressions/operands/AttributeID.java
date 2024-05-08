package ast.expressions.operands;

import ast.Josito;
import ast.preamble.Attribute;
import exceptions.VisibilityException;

public class AttributeID extends Field {
    
    public AttributeID(String v, int row) {
        super(v, row);
    }
    
    @Override
    public void bind() {
        /* Nothing to do */
    }

    @Override
    public void checkType() throws Exception { // TODO posiblemente haya que "bindear" a la definición del atributo cuando se tipe
        try {
            Attribute matched = classFrom.hasAttribute(this, accessingFromInside);
            this.type = matched.getType();
        } catch (Exception e) {
            throw new VisibilityException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return this.fieldname;
    }

    @Override //TODO
    public void generateCode(Josito jose) { // TODO, conseguir el delta del atributo correspondiente en la clase
        // int delta = 
        //jose.createConst(delta);
    }
}
