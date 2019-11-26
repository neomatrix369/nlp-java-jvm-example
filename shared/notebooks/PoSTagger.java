import java.io.InputStream;
import java.io.FileInputStream;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.Sequence;
import java.util.Arrays;

public class PoSTagger {
    public static void main(String [] args) throws Exception {
    System.out.println("[Started...]");
    try (InputStream modelIn = new FileInputStream("en-pos-maxent.bin")) {
        POSModel model = new POSModel(modelIn);
        POSTaggerME tagger = new POSTaggerME(model);

        // The sentence has to be split into words and passed to the POS Tagger function
        String sentence[] = new String[]{"Most", "large", "cities", "in", "the", "US", "had",
                                 "morning", "and", "afternoon", "newspapers", "."};
        System.out.println("Sentence: " + Arrays.toString(sentence));
        String tags[] = tagger.tag(sentence);
        System.out.println(Arrays.toString(tags));
        System.out.println();
        
        System.out.println("Probabilities of tags: ");
        double tagProbabilities[] = tagger.probs();
        Arrays.stream(tagProbabilities).forEach(System.out::println);
        System.out.println();
        
        System.out.println("Tags as sequences (contains probabilities: ");
        Sequence topSequences[] = tagger.topKSequences(sentence);
        System.out.println(Arrays.toString(topSequences));
    }
    System.out.println("[...Finished]");
    }
}