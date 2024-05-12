package coding.JUnitTests;

import org.junit.Test;

public class code004 extends AbstractCode {
    public static final String NAME = "code004";                  // nombre del test sin el java
    public static final String ANSWER = "5";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code004() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}