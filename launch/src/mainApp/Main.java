package mainApp;
import java.io.FileWriter;
import java.io.IOException;

import ast.ASTNode;
import ast.Josito;
import lexicon.ReconLexicon;
import syntax.ReconSyntax;
import utils.Utils;

public class Main {

    public static void main(String[] args) throws Exception {
        Utils.clear();
        ReconLexicon alex = new ReconLexicon();
        ReconSyntax asint = new ReconSyntax();
        Josito codeGenerator = new Josito();
        ASTNode root = asint.main(args);
        System.out.println("------------AST created");
        root.bind();
        System.out.println("------------Binding done");
		root.checkType();
        System.out.println("------------Typing done");
		root.generateCode(codeGenerator);
		writeCode(codeGenerator.toString());
        System.out.println("------------Code generation done: Main.wat created");
        wat2wasm();
        System.out.println("------------Code conversion done: Main.wat -> Main.wasm");
    }

    private static void writeCode(String content) {
        String filePath = "./launch/src/webAssembly/Main.wat";
        try {
            // Create a FileWriter object
            FileWriter writer = new FileWriter(filePath);
            // Write some text to the file
            writer.write(content);
            // Close the writer
            writer.close();
        } catch (IOException e) {
            System.out.println("The code could not be saved");
        }
    }

    private static void wat2wasm() {
        ProcessBuilder pBuilder = new ProcessBuilder(".\\launch\\src\\webAssembly\\WatToWasm.bat");
        pBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        try {
            Process wat2wasm = pBuilder.start();
            // Espera a que el proceso termine
            int exitCode = wat2wasm.waitFor();
            if (exitCode != 0)
                System.err.println("Error al generar Main.wasm. Código de salida: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
