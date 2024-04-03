package launch.test.lexicon.JunitTests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import launch.src.lexicon.ALexOperations;
import launch.src.lexicon.ReconLexicon;
import lexicon.src.*;


public class typedefConstantsCorrect {

    @Test
    public void testtypedefConstantsCorrect() throws FileNotFoundException, IOException {
        String[] args = {"lexicon/test/testingCodes/typedefConstantsCorrect.cpm"}; // File that will be tested
        ReconLexicon.main(args);
        
        // this test shouldnt detect any errors in the lexic
        assertEquals(false, ALexOperations.errorDetected);
    }

}
