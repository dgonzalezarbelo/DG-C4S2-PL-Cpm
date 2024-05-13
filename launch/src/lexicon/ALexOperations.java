package lexicon;

import syntax.LexiconClass;

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
      return new LexiconUnit(alex.row(),alex.col(), LexiconClass.ID, alex.lexeme()); 
   } 
   public LexiconUnit integerUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INTEGER, alex.lexeme()); 
   }
   public LexiconUnit binaryUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INTEGER, convertToDec(alex.lexeme(), 2)); 
   }
   public LexiconUnit hexUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INTEGER, convertToDec(alex.lexeme(), 16)); 
   }
   public LexiconUnit int_tUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INT_T); 
   }
   public LexiconUnit sum_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SUM_OP); 
   } 
   public LexiconUnit subs_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SUBS_OP); 
   } 
   public LexiconUnit mult_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.MULT_OP); 
   } 
   public LexiconUnit div_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.DIV_OP); 
   }
   public LexiconUnit mod_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.MOD_OP); 
   }
   public LexiconUnit pow_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.POW_OP); 
   }  
   public LexiconUnit parentesis_openUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.PARENTESIS_OPEN); 
   } 
   public LexiconUnit parentesis_closeUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.PARENTESIS_CLOSE); 
   }
   public LexiconUnit less_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.LESS_OP); 
   }
   public LexiconUnit greater_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.GREATER_OP); 
   }
   public LexiconUnit equal_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.EQUAL_OP); 
   }
   public LexiconUnit less_or_eq_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.LESS_OR_EQ_OP); 
   }
   public LexiconUnit greater_or_eqUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.GREATER_OR_EQ_OP); 
   }
   public LexiconUnit not_equal_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.NOT_EQUAL_OP); 
   }
   public LexiconUnit boolean_tUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.BOOLEAN_T); 
   }
   public LexiconUnit and_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.AND_OP); 
   }
   public LexiconUnit or_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.OR_OP); 
   }
   public LexiconUnit not_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.NOT_OP); 
   }
   public LexiconUnit trueUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.TRUE); 
   }
   public LexiconUnit falseUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.FALSE); 
   }
   public LexiconUnit commaUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.COMMA); 
   }
   public LexiconUnit colonUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.COLON); 
   }
   public LexiconUnit semicolonUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SEMICOLON); 
   }
   public LexiconUnit class_tUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.CLASS_T); 
   }
   public LexiconUnit struct_tUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.STRUCT_T); 
   }
   public LexiconUnit field_accessUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.FIELD_ACCESS); 
   }
   public LexiconUnit bracket_openUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.BRACKET_OPEN); 
   }
   public LexiconUnit bracket_closeUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.BRACKET_CLOSE); 
   } 
   public LexiconUnit sq_bracket_openUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SQ_BRACKET_OPEN); 
   } 
   public LexiconUnit sq_bracket_closeUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SQ_BRACKET_CLOSE); 
   } 
   public LexiconUnit func_tUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.FUNC_T); 
   }
   public LexiconUnit pointer_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.POINTER_OP); 
   }
   public LexiconUnit reference_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.REFERENCE_OP); 
   } 
   public LexiconUnit new_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.NEW_OP); 
   }
   public LexiconUnit typedefUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.TYPEDEF); 
   } 
   public LexiconUnit defineUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.DEFINE); 
   } 
   public LexiconUnit assignation_opUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.ASSIGNATION_OP); 
   } 
   public LexiconUnit ifUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.IF); 
   } 
   public LexiconUnit elseUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.ELSE); 
   }
   public LexiconUnit switchUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.SWITCH); 
   } 
   public LexiconUnit caseUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.CASE); 
   } 
   public LexiconUnit breakUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.BREAK); 
   } 
   public LexiconUnit defaultUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.DEFAULT); 
   } 
   public LexiconUnit whileUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.WHILE); 
   } 
   public LexiconUnit forUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.FOR); 
   } 
   public LexiconUnit continueUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.CONTINUE); 
   } 
   public LexiconUnit cinUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.INPUT_FUNC); 
   } 
   public LexiconUnit coutUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.OUTPUT_FUNC); 
   } 
   public LexiconUnit returnUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.RETURN); 
   } 
   public LexiconUnit thisUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.THIS);
   }
   public LexiconUnit publicUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.PUBLIC);
   }
   public LexiconUnit privateUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.PRIVATE);
   }
   public LexiconUnit mainUnit() {
      numberLexiconUnits++; //JUnits test:
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.MAIN);
   }
   public LexiconUnit unidadEof() {
      return new LexiconUnit(alex.row(),alex.col(),LexiconClass.EOF); 
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


