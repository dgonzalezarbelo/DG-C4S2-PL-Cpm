package coding.JUnitTests;

import org.junit.Test;

public class code018 extends code {
    public static final String NAME = "code018";                  // nombre del test sin el java
    public static final String ANSWER = "0\n1\n1";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code018() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
