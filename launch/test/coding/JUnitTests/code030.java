package coding.JUnitTests;

import org.junit.Test;

public class code030 extends code {
    public static final String NAME = "code030";                  // nombre del test sin el java
    public static final String ANSWER = "1\n45\n5\n5\n2002\n2002\n8\n8\n10\n10";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code030() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
