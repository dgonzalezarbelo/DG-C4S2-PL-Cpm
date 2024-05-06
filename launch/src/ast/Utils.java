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

    public static void printError(String msg, int row) {
        System.out.println(msg);
        printErrorRow(row);
    }

    public static void printErrorRow(int row) {
        System.out.println("↑ ERROR row: " + row);
    }

    private static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Códigos ANSI para colores de texto
    public static final String RESET = "\033[0m";
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";
}