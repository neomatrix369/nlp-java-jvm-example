import java.io.InputStream;
import java.io.FileInputStream;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.util.Span;
import opennlp.tools.util.Span;
import java.util.Arrays;

public class NameFinderPerson {
    public static void main(String [] args) throws Exception {
        System.out.println("[Started...]");
        try (InputStream modelIn = new FileInputStream("en-ner-person.bin")) {
           TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
           NameFinderME nameFinder = new NameFinderME(model);
           // The sentence has to be split into words and passed to the Name finder function
           String documents[][][] = new String[][][] {{{"Pierre","is", "from", "Paris", "France."}, {"John", "is", "from", "London", "England."}}};
           for (String document[][]: documents) {
              for (String sentence[]: document) {
                  System.out.println("Sentence: " + Arrays.toString(sentence));
                  Span nameSpans[] = nameFinder.find(sentence);
                  System.out.println(Arrays.toString(nameSpans));
              }
              nameFinder.clearAdaptiveData();
           }
        }
        System.out.println("[...Finished]");
    }
}