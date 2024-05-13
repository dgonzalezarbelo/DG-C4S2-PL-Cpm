package coding.JUnitTests;

import org.junit.Test;

public class code011 extends code {
    public static final String NAME = "code011";                                            // nombre del test sin el java
    public static final String ANSWER = "0\n0\n1234\n80\n1\n0\n4321\n2\n0\n0\n1234\n80";    // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code011() {
        super(NAME, ANSWER);
    }

        @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}