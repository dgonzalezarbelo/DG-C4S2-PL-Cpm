package coding.JUnitTests;

import org.junit.Test;

public class code020 extends code {
    public static final String NAME = "code020";                    // nombre del test sin el java
    public static final String ANSWER = "100\n654729075";          // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code020() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
