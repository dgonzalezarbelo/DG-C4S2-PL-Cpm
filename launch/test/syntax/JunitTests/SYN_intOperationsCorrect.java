package syntax.JunitTests;
import syntax.ReconSyntax;

import org.junit.Test;

public class SYN_intOperationsCorrect {
    
    @Test
    public void testSYN_intOperationsCorrect() throws Exception {
        String args ="launch/test/syntax/testingCodes/Correct/SYN_intOperationsCorrect.cpm"; // File that will be tested
        ReconSyntax r = new ReconSyntax();
r.run(args); // this test shouldnt detect any errors in the syntax
    }
}

