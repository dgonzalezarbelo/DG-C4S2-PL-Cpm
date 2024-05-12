package coding.JUnitTests;

import org.junit.Test;

public class code001 extends code {
    public static final String NAME = "code001";                  // nombre del test sin el java
    public static final String ANSWER = "1\n3\n1\n3";    // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code001() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}