import java.io.InputStream;
import java.io.FileInputStream;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.util.Span;
import java.util.Arrays;

public class NameFinderPerson {
    public static void main(String [] args) throws Exception {
        System.out.println("[Started...]");
        String sentence = getSentence(args, "Pierre is from Paris France.");
        String[] sentenceAsWords = sentence.split(" ");
        String documents[][][] = new String[][][] {{sentenceAsWords, {"John", "is", "from", "London", "England."}}};
        try (InputStream modelIn = new FileInputStream("en-ner-person.bin")) {
           TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
           NameFinderME nameFinder = new NameFinderME(model);
           // The sentence has to be split into words and passed to the Name finder function           
           for (String document[][]: documents) {
              for (String eachSentence[]: document) {
                  System.out.println("Sentence: " + Arrays.toString(eachSentence));
                  Span nameSpans[] = nameFinder.find(eachSentence);
                  System.out.println(Arrays.toString(nameSpans));
              }
              nameFinder.clearAdaptiveData();
           }
        }
        System.out.println("[...Finished]");
    }
    
    public static String getSentence(String [] args, String defaultString) {
        String result = defaultString;

        if (args.length > 0) {
            result = args[0];
        }

        return result;
    }
}