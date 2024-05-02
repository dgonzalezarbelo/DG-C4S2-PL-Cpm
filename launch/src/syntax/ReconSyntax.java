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
		Utils.clearConsole();
		Program root = (Program) asint.parse().value;
		root.bind();
		root.checkType();
		System.out.println(root);
	}
}   