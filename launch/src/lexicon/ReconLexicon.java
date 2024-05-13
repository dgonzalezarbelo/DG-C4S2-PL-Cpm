package lexicon;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java_cup.internal_error;
import syntax.LexiconClass;

public class ReconLexicon {
  public void run(String args) throws internal_error, Exception {
    Reader input = new InputStreamReader(new FileInputStream(args));
    AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
    LexiconUnit unidad;

    do {
      unidad = (LexiconUnit) al.next_token();
      System.out.println(unidad);
    } while (unidad.lexiconClass() != LexiconClass.EOF);
  }        
} 
