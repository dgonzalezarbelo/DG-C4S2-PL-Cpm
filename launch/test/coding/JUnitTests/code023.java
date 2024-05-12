package coding.JUnitTests;

import org.junit.Test;

public class code023 extends code {
    public static final String NAME = "code023";                  // nombre del test sin el java
    public static final String ANSWER = "1\n1000\n2\n1000\n-1000\n2\n1000\n-1000\n1\n1000";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code023() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
