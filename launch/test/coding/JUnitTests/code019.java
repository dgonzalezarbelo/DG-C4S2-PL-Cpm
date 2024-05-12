package coding.JUnitTests;

import org.junit.Test;

public class code019 extends code {
    public static final String NAME = "code019";                  // nombre del test sin el java
    public static final String ANSWER = "10\n10\n5\n5\n1\n1\n1\n5\n-1\n-1\n-1\n5\n-10\n-10\n-10";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code019() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}