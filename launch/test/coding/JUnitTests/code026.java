package coding.JUnitTests;

import org.junit.Test;

public class code026 extends code {
    public static final String NAME = "code026";                    // nombre del test sin el java
    public static final String ANSWER = "110\n110\n90\n90\n210\n210\n190\n190\n310\n310\n290\n290\n410\n410\n390\n390\n110\n110\n90\n90\n210\n210\n190\n190\n310\n310\n290\n290\n410\n410\n390\n390";          // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code026() {
        super(NAME, ANSWER);
    }

    @Test
    public void testCode() {
        this.testGeneracionEjecucion();
    }
}
