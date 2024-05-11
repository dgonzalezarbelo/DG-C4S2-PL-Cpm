package syntax.JunitTests;
import syntax.ReconSyntax;

import org.junit.Test;

public class SYN_structCorrect {
    
    @Test
    public void testSYN_structCorrect() throws Exception {
        String[] args = { "launch/test/syntax/testingCodes/Correct/SYN_structCorrect.cpm" }; // File that will be tested
        ReconSyntax.main(args); // this test shouldnt detect any errors in the syntax
    }
}

