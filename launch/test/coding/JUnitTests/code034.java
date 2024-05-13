package coding.JUnitTests;

import org.junit.Test;

public class code034 extends code {
    public static final String NAME = "code034";                  // nombre del test sin el java
    public static final String ANSWER = "1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)
    public static final String[] INPUT = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code034() {
        super(NAME, ANSWER, 11, INPUT);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
