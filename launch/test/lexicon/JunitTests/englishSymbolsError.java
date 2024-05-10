package lexicon.JunitTests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import java_cup.internal_error;
import lexicon.ALexOperations;
import lexicon.ReconLexicon;

public class englishSymbolsError {

    @Test
    public void testenglishSymbolsError() throws FileNotFoundException, IOException {
        String[] args = {"lexicon/test/testingCodes/englishSymbolsError.cpm"}; // File that will be tested
        try {
            ReconLexicon.main(args);
        } catch (internal_error e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // this test shouldnt detect any errors in the lexic
        assertEquals(22, ALexOperations.numberErrors);
        assertEquals(true, ALexOperations.errorDetected);
    }

}
