package coding.JUnitTests;

import mainApp.CompilerMain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class code {
    public static final String OUTPUT_FILENAME = "output.txt";
    public static final String OUTPUTS_PATH = "./launch/test/coding/outputs/";                  // NO TOCAR ESTE
    public static final String INPUT_PATH = "./launch/test/coding/testingCodes/";              // NO TOCAR ESTE
    public static final String WAT2WASM_SCRIPT = "wat2wasm.exe";
    public static final String NODE = "node.exe";
    public static final String WASMEXE = "main.js";
    
    private String name;
    private String codePath;                                                                         // Cambiar el nombre de este
    private String testFilesPath;
    private String watFile;
    private String wasmFile;
    private String outputFile;
    private String ANSWER;                                                                           // Escribir la respuesta (cada vez que wasm imprime hace un salto de linea)

    public code(String name, String answer) {
        this.name = name;
        this.codePath = INPUT_PATH + name + ".cpm";
        this.testFilesPath = OUTPUTS_PATH + name + "/";
        this.watFile = this.testFilesPath + name + ".wat";
        this.wasmFile = this.testFilesPath + name + ".wasm";
        this.outputFile = this.testFilesPath + name + ".txt";
        this.ANSWER = answer + "\n";
    }
    
    
    protected void testGeneracionEjecucion() throws AssertionError {
        try {
            erasePreviousOutput();
            // Generación de Main.wat
            generateWatAndWasm();
            // Ejecución de main.js y captura del resultado
            exeWasm();
            // Verificación del resultado esperado
            assertEquals(ANSWER, getOutput()); // Reemplaza 42 con el resultado esperado
        } catch (Exception e) {
            fail("An exception was throwed: " + e.getMessage());
        }
    }

    private void erasePreviousOutput() {
        // Crear un objeto File con la ruta del archivo
        List<File> archivos = new ArrayList<>();
        archivos.add(new File(outputFile));;
        archivos.add(new File(watFile));
        archivos.add(new File(wasmFile));
        // Verificar si el archivo existe
        for (File file : archivos) {
            if (file.exists()) {
                // Si el archivo existe, intenta eliminarlo
                boolean deleted = file.delete();
                if (!deleted)
                    fail("Error during old outputfiles erasing");
            }
        }
    }

    private void generateWatAndWasm() throws Exception {
        String[] args = {this.codePath, "-w " + name, "-d " + testFilesPath };
        CompilerMain c = new CompilerMain();
        c.run(args);
        boolean errorCode = c.errorDetected.toBool();
        String faseError = c.faseError.name();
        try {
            assertEquals(false, errorCode);
        } catch (AssertionError e) {
            fail(this.name + "generateWatAndWasm failed in: " + faseError);
        }
    }

    private void exeWasm() throws Exception {
        String[] args = {CompilerMain.WASM_EXEFILE, CompilerMain.WASMJS_SCRIPT, wasmFile, outputFile};
        Process wat2wasm = Runtime.getRuntime().exec(args);
        // Espera a que el proceso termine
        int exitCode = wat2wasm.waitFor();
        try {
            assertEquals(0, exitCode);
        } catch (AssertionError e) {
            fail("exeWasm failed");
        }
    }

    private String getOutput() throws Exception {
        // Lee el contenido del archivo
        BufferedReader reader = new BufferedReader(new FileReader(this.outputFile));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append("\n");   // Agrega el salto de línea para mantener la misma estructura que el String esperado
        }
        reader.close();
        return content.toString();
    }

}