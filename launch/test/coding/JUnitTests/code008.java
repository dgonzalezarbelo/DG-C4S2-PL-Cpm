package coding.JUnitTests;

import org.junit.Test;

public class code008 extends code {
    public static final String NAME = "code008";                              // nombre del test sin el java
    public static final String ANSWER = "1\n0\n-1000";                      // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code008() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}