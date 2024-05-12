package coding.JUnitTests;

import org.junit.Test;

public class code027 extends code {
    public static final String NAME = "code027";                  // nombre del test sin el java
    public static final String ANSWER = "1234\n56\n10\n3";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code027() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
