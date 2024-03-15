package lexicon;

public class ALexOperations {
  private AnalizadorLexicoTiny alex;
  public ALexOperations(AnalizadorLexicoTiny alex) {
   this.alex = alex;   
  }
  public LexiconUnit unidadId() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.IDEN,
                                         alex.lexema()); 
  } 
  public LexiconUnit unidadEvalua() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.EVALUA); 
  } 
  public LexiconUnit unidadDonde() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.DONDE); 
  } 
  public LexiconUnit unidadEnt() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.ENT,alex.lexema()); 
  }
  public LexiconUnit unidadBin() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.ENT,convertToDec(alex.lexema(), 2)); 
  }
  public LexiconUnit unidadHex() {
    return new LexiconUnit(alex.row(),alex.col(),LexiconClass.ENT,convertToDec(alex.lexema(), 16)); 
  }
  public LexiconUnit unidadReal() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.REAL,alex.lexema()); 
  } 
  public LexiconUnit unidadSuma() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.MAS); 
  } 
  public LexiconUnit unidadResta() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.MENOS); 
  } 
  public LexiconUnit unidadMul() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.POR); 
  } 
  public LexiconUnit unidadDiv() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.DIV); 
  } 
  public LexiconUnit unidadPAp() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.PAP); 
  } 
  public LexiconUnit unidadPCierre() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.PCIERRE); 
  } 
  public LexiconUnit unidadIgual() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.IGUAL); 
  } 
  public LexiconUnit unidadComa() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.COMA); 
  } 
  public LexiconUnit unidadEof() {
     return new LexiconUnit(alex.row(),alex.col(),LexiconClass.EOF); 
  }
  public void error() {
    System.err.println("***"+alex.row()+", "+alex.col()+" Caracter inesperado: "+alex.lexema());
  }

  public String convertToDec(String binario, int base) {
    Integer n = Integer.parseInt(binario.substring(2), base);
    return Integer.toString(n.intValue());
  }
}


