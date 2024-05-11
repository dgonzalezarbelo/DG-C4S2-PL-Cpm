package coding.JUnitTests;

import org.junit.Test;

public class code013 extends code {
    public static final String NAME = "code013";                  // nombre del test sin el java
    public static final String ANSWER = "4";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code013() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}