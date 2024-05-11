package coding.JUnitTests;

import mainApp.Main;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class code {
    public String codePath;                                                                         // Cambiar el nombre de este
    public static final String OUTPUT_PATH = ".\\launch\\src\\webAssembly\\output.txt";             // NO TOCAR ESTE
    public String ANSWER;                                                                           // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code(String name, String answer) {
        this.codePath = ".\\launch\\test\\coding\\testingCodes\\" + name + ".cpm";
        this.ANSWER = answer + "\n";
    }
    
    
    protected void testGeneracionEjecucion() {
        // Generación de Main.wat
        generarMainWat();
        // Ejecución de main.js y captura del resultado
        exeWasm();
        // Verificación del resultado esperado
        assertEquals(ANSWER, getOutput(OUTPUT_PATH)); // Reemplaza 42 con el resultado esperado
    }

    private void generarMainWat() {
        String[] args = {this.codePath};
        try {
            Main.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exeWasm() {
        ProcessBuilder pBuilder = new ProcessBuilder(".\\launch\\src\\webAssembly\\wasmExe.bat");
        pBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        try {
            Process wasmExe = pBuilder.start();
            // Espera a que el proceso termine
            int exitCode = wasmExe.waitFor();
            if (exitCode != 0)
                System.err.println("Error al generar Main.wasm. Código de salida: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getOutput(String filePath) {
        // Lee el contenido del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append("\n"); // Agrega el salto de línea para mantener la misma estructura que el String esperado
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Si ocurre algún error al leer el archivo, consideramos que los contenidos no son iguales
        }
    }

}