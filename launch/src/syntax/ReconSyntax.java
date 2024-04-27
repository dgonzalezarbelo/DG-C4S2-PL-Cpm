package syntax;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import ast.Utils;
import ast.preamble.Program;
import lexicon.AnalizadorLexicoTiny;

public class ReconSyntax {
	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(alex);
		Program root = (Program) asint.parse().value;
		Utils.clearConsole();
		root.bind();
		System.out.println(root);
	}
}   