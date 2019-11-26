import java.io.InputStream;
import java.io.FileInputStream;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.Span;
import java.util.Arrays;

public class Tokenize {
    public static void main(String [] args) throws Exception {
        System.out.println("[Started...]");
        try(InputStream modelIn = new FileInputStream("../shared/en-token.bin")) {
            TokenizerModel model = new TokenizerModel(modelIn);
            TokenizerME tokenizer = new TokenizerME(model);
            String sentence = "An input sample sentence.";
            System.out.println("Sentence: " + sentence);    
            String tokens[] = tokenizer.tokenize(sentence);
            System.out.println(Arrays.toString(tokens));
            double tokensProbabilies[] = tokenizer.getTokenProbabilities();
            System.out.println("Probabilities of each of the tokens above");
            Arrays.stream(tokensProbabilies).forEach(System.out::println);
            System.out.println();
            Span tokensUsingSpans[] = tokenizer.tokenizePos(sentence);
            System.out.println(Arrays.toString(tokensUsingSpans));
        }
        System.out.println("[...Finished]");
    }
}