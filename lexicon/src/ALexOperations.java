package lexicon.src;

public class ALexOperations {
   private AnalizadorLexicoTiny alex;

   // the rest of the variables are used by the JUnits tests
   public static boolean errorDetected;
   public static int numberLexiconUnits;
   public static int numberErrors;
   // ------------------------------------------------------

   public ALexOperations(AnalizadorLexicoTiny alex) {
      this.alex = alex;
      
      //JUnits test:
      errorDetected = false;
      numberLexiconUnits = 0;
      numberErrors = 0;
      // ---------------------
   }
   public LexiconUnit idUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(), LexiconClass.ID.ordinal(), alex.lexeme()); 
   } 
   public LexiconUnit integerUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INTEGER.ordinal(), alex.lexeme()); 
   }
   public LexiconUnit binaryUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INTEGER.ordinal(), convertToDec(alex.lexeme(), 2)); 
   }
   public LexiconUnit hexUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INTEGER.ordinal(), convertToDec(alex.lexeme(), 16)); 
   }
   public LexiconUnit int_tUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INT_T.ordinal()); 
   }
   public LexiconUnit sum_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SUM_OP.ordinal()); 
   } 
   public LexiconUnit subs_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SUBS_OP.ordinal()); 
   } 
   public LexiconUnit mult_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.MULT_OP.ordinal()); 
   } 
   public LexiconUnit div_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.DIV_OP.ordinal()); 
   }
   public LexiconUnit pow_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.POW_OP.ordinal()); 
   }  
   public LexiconUnit parentesis_openUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.PARENTESIS_OPEN.ordinal()); 
   } 
   public LexiconUnit parentesis_closeUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.PARENTESIS_CLOSE.ordinal()); 
   }
   public LexiconUnit less_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.LESS_OP.ordinal()); 
   }
   public LexiconUnit greater_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.GREATER_OP.ordinal()); 
   }
   public LexiconUnit equal_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.EQUAL_OP.ordinal()); 
   }
   public LexiconUnit less_or_eq_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.LESS_OR_EQ_OP.ordinal()); 
   }
   public LexiconUnit greater_or_eqUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.GREATER_OR_EQ_OP.ordinal()); 
   }
   public LexiconUnit not_equal_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.NOT_EQUAL_OP.ordinal()); 
   }
   public LexiconUnit boolean_tUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.BOOLEAN_T.ordinal()); 
   }
   public LexiconUnit and_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.AND_OP.ordinal()); 
   }
   public LexiconUnit or_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.OR_OP.ordinal()); 
   }
   public LexiconUnit not_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.NOT_OP.ordinal()); 
   }
   public LexiconUnit trueUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.TRUE.ordinal()); 
   }
   public LexiconUnit falseUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.FALSE.ordinal()); 
   }
   public LexiconUnit commaUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.COMMA.ordinal()); 
   }
   public LexiconUnit colonUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.COLON.ordinal()); 
   }
   public LexiconUnit semicolonUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SEMICOLON.ordinal()); 
   }
   public LexiconUnit class_tUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.CLASS_T.ordinal()); 
   }
   public LexiconUnit struct_tUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.STRUCT_T.ordinal()); 
   }
   public LexiconUnit field_accessUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.FIELD_ACCESS.ordinal()); 
   }
   public LexiconUnit bracket_openUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.BRACKET_OPEN.ordinal()); 
   }
   public LexiconUnit bracket_closeUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.BRACKET_CLOSE.ordinal()); 
   } 
   public LexiconUnit sq_bracket_openUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SQ_BRACKET_OPEN.ordinal()); 
   } 
   public LexiconUnit sq_bracket_closeUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SQ_BRACKET_CLOSE.ordinal()); 
   } 
   public LexiconUnit func_tUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.FUNC_T.ordinal()); 
   }
   public LexiconUnit pointer_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.POINTER_OP.ordinal()); 
   } 
   public LexiconUnit new_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.NEW_OP.ordinal()); 
   }
   public LexiconUnit typedefUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.TYPEDEF.ordinal()); 
   } 
   public LexiconUnit defineUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.DEFINE.ordinal()); 
   } 
   public LexiconUnit assignation_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.ASSIGNATION_OP.ordinal()); 
   } 
   public LexiconUnit ifUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.IF.ordinal()); 
   } 
   public LexiconUnit elseUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.ELSE.ordinal()); 
   }
   public LexiconUnit switchUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SWITCH.ordinal()); 
   } 
   public LexiconUnit caseUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.CASE.ordinal()); 
   } 
   public LexiconUnit breakUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.BREAK.ordinal()); 
   } 
   public LexiconUnit defaultUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.DEFAULT.ordinal()); 
   } 
   public LexiconUnit whileUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.WHILE.ordinal()); 
   } 
   public LexiconUnit continueUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.CONTINUE.ordinal()); 
   } 
   public LexiconUnit cinUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INPUT_FUNC.ordinal()); 
   } 
   public LexiconUnit coutUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.OUTPUT_FUNC.ordinal()); 
   } 
   public LexiconUnit returnUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.RETURN.ordinal()); 
   } 
   public LexiconUnit thisUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.THIS.ordinal());
   }
   public LexiconUnit publicUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.PUBLIC.ordinal());
   }
   public LexiconUnit privateUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.PRIVATE.ordinal());
   }
   public LexiconUnit mainUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.MAIN.ordinal());
   }
   public LexiconUnit unidadEof() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.EOF.ordinal()); 
   }
   public void error() {
      // JUnits tests
      errorDetected = true;
      numberErrors++;
      System.err.println("$$$NumberErrors: " + numberErrors);
      // -------------------

      System.err.println("$$$"+alex.row()+", "+alex.col()+" Caracter inesperado: "+alex.lexeme());

   }

   private String convertToDec(String binario, int base) {
      Integer n = Integer.parseInt(binario.substring(2), base);
      return Integer.toString(n.intValue());
   }
}


