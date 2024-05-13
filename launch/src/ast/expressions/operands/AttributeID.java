package ast.expressions.operands;

import ast.Josito;
import ast.preamble.Attribute;
import ast.types.interfaces.Type.Type_T;
import exceptions.VisibilityException;

public class AttributeID extends Field {
    private Attribute matchedAttribute;
    
    public AttributeID(String v, int row) {
        super(v, row);
    }
    
    @Override
    public void bind() {
        /* Nothing to do */
    }

    @Override
    public void checkType() throws Exception {
        try {
            matchedAttribute = classFrom.hasAttribute(this, accessingFromInside);
            this.type = matchedAttribute.getType();
        } catch (Exception e) {
            throw new VisibilityException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return this.fieldname;
    }

    @Override
    public void generateAddress(Josito jose) {
        jose.createConst(matchedAttribute.getOffset());
    }

    @Override
    public void generateValue(Josito jose) {
        Type_T t = this.type.getKind();
		switch (t) {
            case INT:
            case BOOL:
			case POINTER:
				jose.load();
				break;
			case ARRAY:
			case CLASS:
            case STRUCT:
				// In this case, the returned value is the object reference to copy it later, so with generateAddress everything is done
                break;
            case CONST: // This will only be a define
				// This will never be the case
                break;
            default:
                break;
        }
    }
}
