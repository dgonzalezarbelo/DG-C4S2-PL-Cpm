package coding.JUnitTests;

import org.junit.Test;

public class code014 extends AbstractCode {
    public static final String NAME = "code014";                  // nombre del test sin el java
    public static final String ANSWER = "345";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code014() {
        super(NAME, ANSWER);
    }

        @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
