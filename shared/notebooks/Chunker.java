import java.io.InputStream;
import java.io.FileInputStream;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.util.Sequence;
import java.util.Arrays;

public class Chunker {
    public static void main(String [] args) throws Exception {
        System.out.println("[Started...]");
        try (InputStream modelIn = new FileInputStream("en-chunker.bin")){
          ChunkerModel model = new ChunkerModel(modelIn);
          ChunkerME chunker = new ChunkerME(model);

          String sentence[] = new String[] { "Rockwell", "International", "Corp.", "'s",
            "Tulsa", "unit", "said", "it", "signed", "a", "tentative", "agreement",
            "extending", "its", "contract", "with", "Boeing", "Co.", "to",
            "provide", "structural", "parts", "for", "Boeing", "'s", "747",
            "jetliners", "." };

          String pos[] = new String[] { "NNP", "NNP", "NNP", "POS", "NNP", "NN",
            "VBD", "PRP", "VBD", "DT", "JJ", "NN", "VBG", "PRP$", "NN", "IN",
            "NNP", "NNP", "TO", "VB", "JJ", "NNS", "IN", "NNP", "POS", "CD", "NNS",
            "." };

          String tag[] = chunker.chunk(sentence, pos);
          double probs[] = chunker.probs();
          Sequence topSequences[] = chunker.topKSequences(sentence, pos);
          
          System.out.println("Sentence: " + Arrays.toString(sentence) + "\n");
          System.out.println("Tags chunked: " + Arrays.toString(tag) + "\n");
          System.out.println("Tags chunked (with probabilities): " + Arrays.toString(topSequences) + "\n");
        }
        System.out.println("[...Finished]");
    }
}