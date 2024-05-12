package coding.JUnitTests;

import org.junit.Test;

public class code009 extends AbstractCode {
    public static final String NAME = "code009";                              // nombre del test sin el java
    public static final String ANSWER = "-1\n1\n2";                         // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code009() {
        super(NAME, ANSWER);
    }

        @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}