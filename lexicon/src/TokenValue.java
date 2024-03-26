package lexicon.src;

public class TokenValue {
    public String lexeme;
    public int row;
    public int column;
    
    public TokenValue(int row, int col) {
    	this.row = row;
    	this.column = col;
    }
    public TokenValue(String lexeme, int row, int col) {
    	this.lexeme = lexeme;
    	this.row = row;
    	this.column = col;
    }
}

