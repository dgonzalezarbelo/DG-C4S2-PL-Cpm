package coding.JUnitTests;

import org.junit.Test;

public class code021 extends code {
    public static final String NAME = "code021";                  // nombre del test sin el java
    public static final String ANSWER = "1\n1\n0\n0\n1\n1\n1\n0\n0\n0\n0\n0\n1\n1\n1";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code021() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}