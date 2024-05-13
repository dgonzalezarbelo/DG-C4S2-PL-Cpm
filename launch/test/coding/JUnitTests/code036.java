package coding.JUnitTests;

import org.junit.Test;

public class code036 extends code {
    public static final String NAME = "code036";                  // nombre del test sin el java
    public static final String ANSWER = "0\n1\n2\n3\n4\n0\n1\n2\n3\n4\n0\n1\n2\n3\n4\n0\n1\n2\n3\n4\n0\n1\n2\n3\n4";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code036() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
