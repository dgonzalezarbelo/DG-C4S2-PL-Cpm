package ast;

public class Utils {
    public static void appendIndent(StringBuilder str, int indent) {
        String token = "    ", lastToken = "|---";
        for (int i = 0; i < indent; i++)
            str.append(i == indent - 1 ? lastToken : token);
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    public static void debug(String msg) {
        System.out.println(msg);
    }

    public static void printErrorRow(int row) {
        System.out.println("ERROR row: " + row);
    }
}
