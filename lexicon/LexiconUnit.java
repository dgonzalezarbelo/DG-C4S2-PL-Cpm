package lexicon;

public class LexiconUnit extends Symbol {
    public LexiconUnit(int row, int col, int lexiconClass) {
	    super(lexiconClass,new TokenValue(row,col));
    }
    public LexiconUnit(int fila, int col, int lexiconClass, String lexema) {
	    super(lexiconClass,new TokenValue(lexema,fila,col));
    }
    public int lexiconClass () {return sym;}
    public String lexema() {return ((TokenValue)value).lexeme;}    
    public int row() {return ((TokenValue)value).row;}
    public int col() {return ((TokenValue)value).column;}
    public String toString() {
	if (lexema() == null) {
	    return "[lexiconClass:"+lexiconClass()+",row:"+row()+",col:"+col()+"]";
	} else {
	    return "[lexiconClass:"+lexiconClass()+",row:"+row()+",col:"+col()+",lexeme:"+lexema()+"]";
	}
    }
}
