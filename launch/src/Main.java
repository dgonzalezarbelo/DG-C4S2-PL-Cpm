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
        // System.out.println(codeGenerator.toString());
		writeCode(codeGenerator.toString());
    }

    private static void writeCode(String content) {
        String filePath = "./launch/src/Main.wat";
        
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
}
