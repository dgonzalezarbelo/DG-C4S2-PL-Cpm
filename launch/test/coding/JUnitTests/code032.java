package coding.JUnitTests;

import org.junit.Test;

public class code032 extends code {
    public static final String NAME = "code032";                  // nombre del test sin el java
    public static final String ANSWER = "0\n2\n4\n6\n8";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code032() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
