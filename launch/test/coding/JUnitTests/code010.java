package coding.JUnitTests;

import org.junit.Test;

public class code010 extends code {
    public static final String NAME = "code010";                              // nombre del test sin el java
    public static final String ANSWER = "720";           // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code010() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}