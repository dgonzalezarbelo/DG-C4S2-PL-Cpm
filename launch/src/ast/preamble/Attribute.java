package ast.preamble;

import ast.Utils;
import ast.preamble.Visibility.Visibility_T;
import ast.sentences.declarations.Declaration;

public class Attribute extends Declaration {
    private Visibility visibility;

    public Attribute(Declaration d, Visibility v) {
        super(d);
        this.visibility = v;
    } 

    public Attribute(Declaration d) {
        this(d, new Public_Vis());
    }
    
    public boolean isPublic() {
        return this.visibility.getVisibility() == Visibility_T.PUBLIC;
    }

    public void setVisibility(Visibility v) {
        this.visibility = v;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append((visibility == null ? "" : visibility.toString() + " " )
        + this.type.toString()
        + " " + varname.toString() + '\n');
        return str.toString();
    }
}


