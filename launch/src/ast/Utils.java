package ast;

public class Utils {
    public static void appendIndent(StringBuilder str, int indent) {
        String token = "    ", lastToken = "|---";
        for (int i = 0; i < indent; i++)
            str.append(i == indent - 1 ? lastToken : token);
    }
}
