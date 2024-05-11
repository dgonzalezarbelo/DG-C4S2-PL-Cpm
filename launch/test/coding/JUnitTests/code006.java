package coding.JUnitTests;

import org.junit.Test;

public class code006 extends code {
    public static final String NAME = "code006";                              // nombre del test sin el java
    public static final String ANSWER = "4\n" + "7\n" + "10";    // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code006() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}