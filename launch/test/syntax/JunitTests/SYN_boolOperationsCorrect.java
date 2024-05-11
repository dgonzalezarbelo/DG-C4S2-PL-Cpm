package syntax.JunitTests;
import syntax.ReconSyntax;

import org.junit.Test;

public class SYN_boolOperationsCorrect {
    
    @Test
    public void testSYN_boolOperationsCorrect() throws Exception {
        String[] args = { "launch/test/syntax/testingCodes/Correct/SYN_boolOperationsCorrect.cpm" }; // File that will be tested
        ReconSyntax.main(args); // this test shouldnt detect any errors in the syntax
    }
}
