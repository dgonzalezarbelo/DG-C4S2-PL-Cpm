package lexicon.test.JunitTests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;


import org.junit.Test;

import lexicon.ALexOperations;
import lexicon.ReconLexicon;

public class switchCorrect {
    
    @Test
    public void testswitchCorrect() throws FileNotFoundException, IOException {
        String[] args = {"lexicon/test/testingCodes/switchCorrect.cpm"}; // File that will be tested
        ReconLexicon.main(args);
        
        // this test shouldnt detect any errors in the lexic
        assertEquals(false, ALexOperations.errorDetected);
    }
}
