package coding.JUnitTests;

public class code1 extends code {
    public static final String NAME = "code1";                  // nombre del test sin el java
    public static final String ANSWER = "1\n3\n1\n3" + "\n";    // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code1() {
        super(NAME, ANSWER);
    }
}