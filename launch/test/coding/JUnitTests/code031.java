package coding.JUnitTests;

import org.junit.Test;

public class code031 extends code {
    public static final String NAME = "code031";                  // nombre del test sin el java
    public static final String ANSWER = "27\n1\n9\n81\n3125\n2401\n1\n0\n25\n-1\n-3\n20\n4\n2\n32\n1\n0\n1\n0\n0\n1\n1\n1\n1\n0\n0\n0\n0\n1\n0\n1";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code031() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
