package coding.JUnitTests;

import org.junit.Test;

public class code028 extends code {
    public static final String NAME = "code028";                  // nombre del test sin el java
    public static final String ANSWER = "45\n45\n30\n30";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code028() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
