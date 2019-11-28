import java.io.InputStream;
import java.io.FileInputStream;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.util.Span;
import java.util.Arrays;

public class DetectSentence {
    public static void main(String [] args) throws Exception {
        System.out.println("[Started...]");
        String sentence = getSentence(args, "  First sentence. Second sentence. ");
        try (InputStream modelIn = new FileInputStream("en-sent.bin")) {
          SentenceModel model = new SentenceModel(modelIn);
          SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
          System.out.println("Sentence: " + sentence);
          String sentences[] = sentenceDetector.sentDetect(sentence);
          System.out.println(Arrays.toString(sentences));
          Span sentencesUsingSpan[] = sentenceDetector.sentPosDetect(sentence);
          System.out.println();
          System.out.println(Arrays.toString(sentencesUsingSpan));
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