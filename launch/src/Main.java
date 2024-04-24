import lexicon.ReconLexicon;
import syntax.ReconSyntax;

public class Main {
    
    public static void main(String[] args) throws Exception {
        ReconLexicon alex = new ReconLexicon();
        ReconSyntax asint = new ReconSyntax();
        try {
            asint.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
