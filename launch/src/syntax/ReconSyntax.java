package syntax;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import ast.ASTNode;
import ast.preamble.Program;
import lexicon.AnalizadorLexicoTiny;

public class ReconSyntax {
	
	public ASTNode run(String cpmFilePath) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(cpmFilePath));
		AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(alex);
		Program p;
		try {
			p = (Program) asint.parse().value;
		} catch (Exception e) {
			p = null;
		}
		return p;
	}
}