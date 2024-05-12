package errors;

import lexicon.LexiconUnit;
import mainApp.Main;
import utils.Utils;

public class GestionErroresTiny {
	public void errorLexico(int row, int col, String lexema) {
		Main.errorDetected = true;
		System.out.println("Lexicon error: unexpected character " + lexema);
		Utils.printErrorRowCol(row, col);
	}

	public void errorSintactico(LexiconUnit unidadLexica) {
		Main.errorDetected = true;
		if (unidadLexica.lexema() != null)
			System.out.println("Syntax error: unexpected character '" + unidadLexica.lexema() + "'");
		else
			System.out.println("Syntax error: unexpected character");
		Utils.printErrorRowCol(unidadLexica.row(), unidadLexica.col());
	}
}
