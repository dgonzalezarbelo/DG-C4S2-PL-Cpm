package lexicon.test.JunitTests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import lexicon.src.*;;

public class spanishSymbolsError {

    @Test
    public void testspanishSymbolsError() throws FileNotFoundException, IOException {
        String[] args = {"lexicon/test/testingCodes/spanishSymbolsError.cpm"}; // File that will be tested
        ReconLexicon.main(args);
        
        // this test shouldnt detect any errors in the lexic
        assertEquals(12, ALexOperations.numberErrors); //6 double errors
        assertEquals(true, ALexOperations.errorDetected);
    }

}

