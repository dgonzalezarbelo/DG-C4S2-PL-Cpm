package coding.JUnitTests;

import org.junit.Test;

public class code025 extends code {
    public static final String NAME = "code025";                    // nombre del test sin el java
    public static final String ANSWER = "0\n1\n2\n3\n4\n5\n6\n7";          // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code025() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
