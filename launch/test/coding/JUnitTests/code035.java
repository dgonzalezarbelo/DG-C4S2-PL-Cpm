package coding.JUnitTests;

import org.junit.Test;

public class code035 extends code {
    public static final String NAME = "code035";                  // nombre del test sin el java
    public static final String ANSWER = "-15\n-15\n-15\n1500\n1500\n1500\n0\n0\n0";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code035() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}