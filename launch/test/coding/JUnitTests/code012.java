package coding.JUnitTests;

import org.junit.Test;

public class code012 extends code {
    public static final String NAME = "code012";                  // nombre del test sin el java
    public static final String ANSWER = "1\n100\n100";    // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code012() {
        super(NAME, ANSWER);
    }

        @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}