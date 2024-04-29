package ast.preamble;

import ast.Utils;
import ast.sentences.declarations.Declaration;
import exceptions.DuplicateDefinitionException;

public class Attribute extends Declaration {
    private Visibility visibility;

    public Attribute(Declaration d, Visibility v) {
        super(d);
        this.visibility = v;
    } 

    public void setVisibility(Visibility v) {
        this.visibility = v;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append((visibility == null ? "" : visibility.toString() + " " )
        + this.type.toString()
        + " " + id.toString() + '\n');
        return str.toString();
    }

    @Override
	public void bind() {
        type.bind();
        try {
            Program.symbolsTable.insertSymbol(id.getValue(), this);
        }
        catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
	}
}


