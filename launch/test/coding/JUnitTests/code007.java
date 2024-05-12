package coding.JUnitTests;

import org.junit.Test;

public class code007 extends code {
    public static final String NAME = "code007";                              // nombre del test sin el java
    public static final String ANSWER = "1\n3\n3\n3\n2\n0\n2\n0";           // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code007() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}