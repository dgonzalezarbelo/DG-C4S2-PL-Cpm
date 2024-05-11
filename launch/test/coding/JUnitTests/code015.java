package coding.JUnitTests;

import org.junit.Test;

public class code015 extends code {
    public static final String NAME = "code015";                  // nombre del test sin el java
    public static final String ANSWER = "345";                 // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code015() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
