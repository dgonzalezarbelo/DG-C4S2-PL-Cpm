package coding.JUnitTests;

import org.junit.Test;

public class code037 extends code {
    public static final String NAME = "code037";                  // nombre del test sin el java
    public static final String ANSWER = "10\n12\n14\n16\n18\n18\n16\n14\n12\n10";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code037() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
