import java.io.InputStream;
import java.io.FileInputStream;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.Sequence;
import java.util.Arrays;

public class PoSTagger {
    public static void main(String [] args) throws Exception {
        System.out.println("[Started...]");
        String sentence = getSentence(args, "Most large cities in the US had morning and afternoon newspapers .");

        // The sentence has to be split into words and passed to the POS Tagger function
        String sentenceAsWords[] = sentence.split(" ");

        try (InputStream modelIn = new FileInputStream("en-pos-maxent.bin")) {
            POSModel model = new POSModel(modelIn);
            POSTaggerME tagger = new POSTaggerME(model);

            System.out.println("Sentence: " + Arrays.toString(sentenceAsWords));
            String tags[] = tagger.tag(sentenceAsWords);
            System.out.println(Arrays.toString(tags));
            System.out.println();
            
            System.out.println("Probabilities of tags: ");
            double tagProbabilities[] = tagger.probs();
            Arrays.stream(tagProbabilities).forEach(System.out::println);
            System.out.println();
            
            System.out.println("Tags as sequences (contains probabilities: ");
            Sequence topSequences[] = tagger.topKSequences(sentenceAsWords);
            System.out.println(Arrays.toString(topSequences));
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