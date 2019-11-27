import java.io.InputStream;
import java.io.FileInputStream;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.cmdline.parser.ParserTool; 
import opennlp.tools.util.Sequence;
import java.util.Arrays;

public class ParserChunking {
    public static void main(String [] args) throws Exception {
      System.out.println("[Started...]");
      try (InputStream modelIn = new FileInputStream("en-parser-chunking.bin")){

        ParserModel model = new ParserModel(modelIn);
        Parser parser = ParserFactory.create(model);

        String sentence = "The quick brown fox jumps over the lazy dog.";
        Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
        
        System.out.println("Sentence: " + sentence + "\n");
        Arrays.stream(topParses).forEach(System.out::println);
      }
      System.out.println("[...Finished]");
    }
}