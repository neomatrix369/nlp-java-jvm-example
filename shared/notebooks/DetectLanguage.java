import java.io.InputStream;
import java.io.FileInputStream;
import opennlp.tools.langdetect.LanguageDetectorModel;
import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetector;
import opennlp.tools.langdetect.Language;
import java.util.Arrays;

public class DetectLanguage {
    public static void main(String [] args) throws Exception {
        System.out.println("[Started...]");
        String sentence = getSentence(args, "This is a sample text.");
        try (InputStream modelIn = new FileInputStream("langdetect-183.bin")) {
            LanguageDetectorModel langModel = new LanguageDetectorModel(modelIn);
            System.out.println("Sentence: " + sentence);

            // Get the most probable language
            LanguageDetector myCategorizer = new LanguageDetectorME(langModel);
            Language bestLanguage = myCategorizer.predictLanguage(sentence);
            System.out.println("Best language: " + bestLanguage.getLang());
            System.out.println("Best language confidence: " + bestLanguage.getConfidence());

            // Get an array with the most probable languages
            Language[] languages = myCategorizer.predictLanguages("");
            System.out.println("");
            System.out.println("Predict languages (with confidence): " + Arrays.toString(languages));
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