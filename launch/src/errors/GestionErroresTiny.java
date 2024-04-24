package errors;

import lexicon.LexiconUnit;

public class GestionErroresTiny {
   public void errorLexico(int row, int col, String lexema) {
     System.out.println("ERROR row "+row+" col "+col+": Caracter inesperado: "+lexema); 
   }  
   public void errorSintactico(LexiconUnit unidadLexica) {
     if (unidadLexica.lexema() != null) {
       System.out.println("ERROR row "+unidadLexica.row()+" col "+unidadLexica.col()+": Elemento inesperado \""+unidadLexica.lexema()+"\"");
     } else {
       System.out.println("ERROR row "+unidadLexica.row()+" col "+unidadLexica.col()+": Elemento inesperado");
     }
   }
}
