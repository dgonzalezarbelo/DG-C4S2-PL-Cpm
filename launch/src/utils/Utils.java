package utils;


import java.util.Set;

public class Utils {

    public static void appendIndent(StringBuilder str, int indent) {
        String token = "    ", lastToken = "|---";
        for (int i = 0; i < indent; i++)
            str.append(i == indent - 1 ? lastToken : token);
    }

    public static Integer getMinSet(Set<Integer> list) {
        if (list == null || list.size() == 0) return null;
        Integer ret = Integer.MAX_VALUE;
        for (Integer i: list)
            if (i <= ret) ret = i;
        return ret;
    }

    public static Integer getMaxSet(Set<Integer> list) {
        if (list == null || list.size() == 0) return null;
        Integer ret = Integer.MIN_VALUE;
        for (Integer i: list)
            if (i >= ret) ret = i;
        return ret;
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

    public static void clear() {
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