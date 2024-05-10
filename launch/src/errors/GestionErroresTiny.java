package errors;

import lexicon.LexiconUnit;
import utils.Utils;

public class GestionErroresTiny {
	public void errorLexico(int row, int col, String lexema) {
		System.out.println("Lexicon error: Caracter " + lexema + " inesperado");
		Utils.printErrorRowCol(row, col);
	}

	public void errorSintactico(LexiconUnit unidadLexica) {
		if (unidadLexica.lexema() != null)
			System.out.println("Sintax error: Elemento inesperado \"" + unidadLexica.lexema() + "\"");
		else
			System.out.println("Sintax error: Elemento inesperado");
		Utils.printErrorRowCol(unidadLexica.row(), unidadLexica.col());
	}
}
