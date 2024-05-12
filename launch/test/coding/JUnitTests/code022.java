package coding.JUnitTests;

import org.junit.Test;

public class code022 extends code {
    public static final String NAME = "code022";                  // nombre del test sin el java
    public static final String ANSWER = "1234\n21\n0\n1000\n0\n1234\n21\n1\n1000\n1\n4321\n20\n1\n1000\n2";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code022() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}