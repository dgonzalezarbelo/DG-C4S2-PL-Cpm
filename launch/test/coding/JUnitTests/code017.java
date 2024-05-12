package coding.JUnitTests;

import org.junit.Test;

public class code017 extends AbstractCode {
    public static final String NAME = "code017";                  // nombre del test sin el java
    public static final String ANSWER = "100";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code017() {
        super(NAME, ANSWER);
    }

        @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
