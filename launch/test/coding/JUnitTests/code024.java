package coding.JUnitTests;

import org.junit.Test;

public class code024 extends code {
    public static final String NAME = "code024";                  // nombre del test sin el java
    public static final String ANSWER = "0\n0\n3\n3";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code024() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
