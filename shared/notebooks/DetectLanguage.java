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
        try (InputStream modelIn = new FileInputStream("langdetect-183.bin")) {
            LanguageDetectorModel langModel = new LanguageDetectorModel(modelIn);
            String inputText = "This is a sample text.";
            System.out.println("Sentence: " + inputText);

            // Get the most probable language
            LanguageDetector myCategorizer = new LanguageDetectorME(langModel);
            Language bestLanguage = myCategorizer.predictLanguage(inputText);
            System.out.println("Best language: " + bestLanguage.getLang());
            System.out.println("Best language confidence: " + bestLanguage.getConfidence());

            // Get an array with the most probable languages
            Language[] languages = myCategorizer.predictLanguages("");
            System.out.println("");
            System.out.println("Predict languages (with confidence): " + Arrays.toString(languages));
        }
        System.out.println("[...Finished]");
    }
}