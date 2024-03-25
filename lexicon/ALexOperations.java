package lexicon;

public class ALexOperations {
   private AnalizadorLexicoTiny alex;
   public ALexOperations(AnalizadorLexicoTiny alex) {
      this.alex = alex;   
   }
   public LexiconUnit idUnit() {
      return new LexiconUnit(alex.row(),alex.col(), LexiconClass.ID.ordinal(), alex.lexeme()); 
   } 
   public LexiconUnit integerUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INTEGER.ordinal(), alex.lexeme()); 
   }
   public LexiconUnit binaryUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INTEGER.ordinal(), convertToDec(alex.lexeme(), 2)); 
   }
   public LexiconUnit hexUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INTEGER.ordinal(), convertToDec(alex.lexeme(), 16)); 
   }
   public LexiconUnit int_tUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INT_T.ordinal()); 
   }
   public LexiconUnit sum_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SUM_OP.ordinal()); 
   } 
   public LexiconUnit subs_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SUBS_OP.ordinal()); 
   } 
   public LexiconUnit mult_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.MULT_OP.ordinal()); 
   } 
   public LexiconUnit div_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.DIV_OP.ordinal()); 
   }
   public LexiconUnit pow_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.POW_OP.ordinal()); 
   }  
   public LexiconUnit parentesis_openUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.PARENTESIS_OPEN.ordinal()); 
   } 
   public LexiconUnit parentesis_closeUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.PARENTESIS_CLOSE.ordinal()); 
   }
   public LexiconUnit less_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.LESS_OP.ordinal()); 
   }
   public LexiconUnit greater_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.GREATER_OP.ordinal()); 
   }
   public LexiconUnit equal_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.EQUAL_OP.ordinal()); 
   }
   public LexiconUnit less_or_eq_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.LESS_OR_EQ_OP.ordinal()); 
   }
   public LexiconUnit greater_or_eqUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.GREATER_OR_EQ_OP.ordinal()); 
   }
   public LexiconUnit not_equal_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.NOT_EQUAL_OP.ordinal()); 
   }
   public LexiconUnit boolean_tUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.BOOLEAN_T.ordinal()); 
   }
   public LexiconUnit and_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.AND_OP.ordinal()); 
   }
   public LexiconUnit or_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.OR_OP.ordinal()); 
   }
   public LexiconUnit not_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.NOT_OP.ordinal()); 
   }
   public LexiconUnit trueUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.TRUE.ordinal()); 
   }
   public LexiconUnit falseUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.FALSE.ordinal()); 
   }
   public LexiconUnit commaUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.COMMA.ordinal()); 
   }
   public LexiconUnit colonUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.COLON.ordinal()); 
   }
   public LexiconUnit semicolonUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SEMICOLON.ordinal()); 
   }
   public LexiconUnit class_tUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.CLASS_T.ordinal()); 
   }
   public LexiconUnit struct_tUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.STRUCT_T.ordinal()); 
   }
   public LexiconUnit field_accessUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.FIELD_ACCESS.ordinal()); 
   }
   public LexiconUnit bracket_openUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.BRACKET_OPEN.ordinal()); 
   }
   public LexiconUnit bracket_closeUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.BRACKET_CLOSE.ordinal()); 
   } 
   public LexiconUnit sq_bracket_openUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SQ_BRACKET_OPEN.ordinal()); 
   } 
   public LexiconUnit sq_bracket_closeUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SQ_BRACKET_CLOSE.ordinal()); 
   } 
   public LexiconUnit func_tUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.FUNC_T.ordinal()); 
   }
   public LexiconUnit pointer_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.POINTER_OP.ordinal()); 
   } 
   public LexiconUnit new_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.NEW_OP.ordinal()); 
   }
   public LexiconUnit typedefUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.TYPEDEF.ordinal()); 
   } 
   public LexiconUnit defineUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.DEFINE.ordinal()); 
   } 
   public LexiconUnit assignation_opUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.ASSIGNATION_OP.ordinal()); 
   } 
   public LexiconUnit ifUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.IF.ordinal()); 
   } 
   public LexiconUnit elseUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.ELSE.ordinal()); 
   }
   public LexiconUnit switchUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SWITCH.ordinal()); 
   } 
   public LexiconUnit caseUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.CASE.ordinal()); 
   } 
   public LexiconUnit breakUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.BREAK.ordinal()); 
   } 
   public LexiconUnit defaultUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.DEFAULT.ordinal()); 
   } 
   public LexiconUnit whileUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.WHILE.ordinal()); 
   } 
   public LexiconUnit continueUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.CONTINUE.ordinal()); 
   } 
   public LexiconUnit cinUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INPUT_FUNC.ordinal()); 
   } 
   public LexiconUnit coutUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.OUTPUT_FUNC.ordinal()); 
   } 
   public LexiconUnit returnUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.RETURN.ordinal()); 
   } 
   public LexiconUnit thisUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.THIS.ordinal());
   }
   public LexiconUnit publicUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.PUBLIC.ordinal());
   }
   public LexiconUnit privateUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.PRIVATE.ordinal());
   }
   public LexiconUnit mainUnit() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.MAIN.ordinal());
   }
   public LexiconUnit unidadEof() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.EOF.ordinal()); 
   }
   public void error() {
      System.err.println("$$$"+alex.row()+", "+alex.col()+" Caracter inesperado: "+alex.lexeme());
   }

   private String convertToDec(String binario, int base) {
      Integer n = Integer.parseInt(binario.substring(2), base);
      return Integer.toString(n.intValue());
   }
}


