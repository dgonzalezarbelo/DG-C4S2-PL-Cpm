package ast.preamble;
import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Utils;
import ast.sentences.declarations.Declaration;
import ast.types.Type;
import exceptions.DuplicateDefinitionException;

public class Typedef extends Definition {
    private Type type; // The previously existing type
    
    public Typedef(Declaration dec, int row) {
        super(dec.getId().toString(), row); // We store the new alias in the id
        this.type = dec.getType();
    }

    @Override
    public String toString() {
        return "typedef " + type.toString() + id.toString();
    }

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
    }

    @Override
    public void bind() {
        type.bind();
        try {
            Program.symbolsTable.insertDefinitions(this.id.getName(), this);
        } catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
    }

    @Override
    public List<ASTNode> getReferences() {
        List<ASTNode> list = new ArrayList<>();
        list.add(this);
        return list;
    }
}

//FIXME
/*
taipdef int i
taipdef i entero
 */