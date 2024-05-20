package mainApp;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ast.ASTNode;
import ast.Josito;
import ast.SymbolsTable;
import lexicon.ReconLexicon;
import syntax.ReconSyntax;
import utils.GoodBoolean;
import utils.Utils;

public class CompilerMain {
    public static final String DEFAULT_INPUTFILE_PATH = "./";
    public static final String DEFAULT_INPUTFILE_NAME = "debug.cpm";
    public static enum Fase {ASTGEN, BIND, TYPE, CODEGEN, WATSAVE, SUCCED};

    public GoodBoolean errorDetected;
    public Fase faseError;
    public String inputFilePathName;
    public String outputFilePath;
    public String outputFileName;

    public CompilerMain() {
        this.errorDetected = new GoodBoolean(false);
        this.faseError = Fase.ASTGEN;
    }

    /*
     * arg: ruta y nombre del fichero .cpm a compilar
     * -w arg: nombre del fichero .wat generado 
     * -d arg: nombre de la ruta donde ubicar el .wat
     */
    public void run(String[] args) throws Exception {
        parseArgs(args);
        // Utils.clearConsole();
        ReconLexicon alex = new ReconLexicon();
        ReconSyntax asint = new ReconSyntax();
        Josito codeGenerator = new Josito();
        SymbolsTable symbolsTable = new SymbolsTable();
        ASTNode root = asint.run(inputFilePathName);
        errorDetected.setValue(root == null);
        if (!errorDetected.toBool()) root.propagateStaticVars(errorDetected, symbolsTable);
        printConfirmationMsg("------------ASTNode");
        if (!errorDetected.toBool()) root.bind();
        printConfirmationMsg("------------Binding");
		if (!errorDetected.toBool()) root.checkType();
        printConfirmationMsg("------------Typing");
		if (!errorDetected.toBool()) root.generateCode(codeGenerator);
        printConfirmationMsg("------------Coding");
		if (!errorDetected.toBool()) watGeneration(codeGenerator.toString());
        printConfirmationMsg("------------Main.wat generation");
    }

    private void printConfirmationMsg(String msg) {
        if (!errorDetected.toBool()) {
            System.out.println(msg + " succeed");
            faseError = Fase.values()[faseError.ordinal() + 1];
        }
        else
            System.out.println(msg + " failed");
    }

    private void watGeneration(String content) {
        try {

            // Crear el directorio si no existe
            File directory = new File(outputFilePath);
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                if (created) {
                    System.out.println("Directorio creado exitosamente.");
                } else {
                    System.err.println("Error al crear el directorio.");
                    return; // Salir del programa si no se pudo crear el directorio
                }
            }

            // Create a FileWriter object
            FileWriter writer = new FileWriter(outputFilePath + outputFileName + ".wat");
            // Write some text to the file
            writer.write(content);
            // Close the writer
            writer.close();
        } catch (IOException e) {
            errorDetected.setValue(true);
            Utils.printError("The code could not be saved");
        }
    }

    private void parseArgs(String[] args) {
        Map<String, String> parsedArgs = new HashMap<>();
        parsedArgs.put("default", DEFAULT_INPUTFILE_PATH + DEFAULT_INPUTFILE_NAME);
        parsedArgs.put("d", DEFAULT_INPUTFILE_PATH);

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.startsWith("-")) {
                // Parse short options (-o value)
                String key = arg.substring(1, 2);
                String value = args[i+1];
                parsedArgs.put(key, value);
                i++;
            } else {
                // Parse standalone arguments
                parsedArgs.put("default", arg);
            }
        }

        this.inputFilePathName = parsedArgs.get("default");
        if (parsedArgs.containsKey("w"))
            this.outputFileName = parsedArgs.get("w");
        else {
            StringBuilder str = new StringBuilder();
            str.append(parsedArgs.get("default"));
            str.delete(str.length() - 4, str.length());
            this.outputFileName = str.toString();
        }
        this.outputFilePath = parsedArgs.get("d");
    }
}
