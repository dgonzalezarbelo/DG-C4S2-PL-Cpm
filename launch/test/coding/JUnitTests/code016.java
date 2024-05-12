package coding.JUnitTests;

import org.junit.Test;

public class code016 extends AbstractCode {
    public static final String NAME = "code016";                  // nombre del test sin el java
    public static final String ANSWER = "1";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code016() {
        super(NAME, ANSWER);
    }

        @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
