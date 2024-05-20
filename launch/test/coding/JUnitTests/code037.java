package coding.JUnitTests;

import org.junit.Test;

public class code037 extends code {
    public static final String NAME = "code037";                  // nombre del test sin el java
    public static final String ANSWER = "0\n1\n2\n3\n4\n4\n3\n2\n1\n0";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code037() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
