package lexicon;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java_cup.internal_error;

public class ReconLexicon {
  public static void main(String[] args) throws internal_error, Exception {
    Reader input = new InputStreamReader(new FileInputStream(args[0]));
    AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
    LexiconUnit unidad;

    do {
      unidad = (LexiconUnit) al.next_token();
      System.out.println(unidad);
    } while (unidad.lexiconClass() != LexiconClass.EOF.ordinal());
  }        
} 
