import com.karach.compositetask.exception.CompositeException;
import com.karach.compositetask.model.TextComponent;
import com.karach.compositetask.parser.TextParser;
import com.karach.compositetask.reader.TextReader;
import com.karach.compositetask.reader.impl.TextReaderImpl;
import com.karach.compositetask.service.TextService;
import com.karach.compositetask.service.impl.TextServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    private static final String PATH_TO_TEXT = "C:\\Users\\skarach.DESKTOP-TJ4Q3M8\\IdeaProjects\\CompositeTask\\src\\main\\resources\\уew.txt";

    public static void main(String[] args) throws CompositeException {
        try {
            Path path = Paths.get(PATH_TO_TEXT);
            byte[] bytes = Files.readAllBytes(path);
            String text = new String(bytes, StandardCharsets.UTF_8);
            System.out.println(text);

            String filename="уew.txt";
            Path pathToFile = Paths.get(filename);
            System.out.println("Path" + pathToFile.toAbsolutePath());

            TextReader reader = new TextReaderImpl();
            TextService textService = new TextServiceImpl(reader, new TextParser() {
                @Override
                public TextComponent parse(String text) {
                    return null;
                }
            });

            TextComponent textComponent = textService.parseText(text);

            if (textComponent != null) {
                textService.sortParagraphsBySentenceCount(textComponent);
                TextComponent longestWordSentence = textService.findSentencesWithLongestWord(textComponent);
                textService.removeSentencesWithWordCountLessThan(textComponent, 5);
                int wordOccurrences = textService.countOccurrencesOfWordsIgnoreCase(textComponent, "Lorem");
                textService.countVowelsAndConsonantsInSentence(textComponent);

                String composedText = textService.composeText(textComponent);
                System.out.println(composedText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}