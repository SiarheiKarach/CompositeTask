package com.karach.compositetask.main;

import com.karach.compositetask.exception.CompositeException;
import com.karach.compositetask.model.TextComponent;
import com.karach.compositetask.parser.TextParser;
import com.karach.compositetask.parser.impl.SentenceTextParser;
import com.karach.compositetask.parser.impl.SymbolTextParser;
import com.karach.compositetask.reader.TextReader;
import com.karach.compositetask.reader.impl.TextReaderImpl;
import com.karach.compositetask.service.TextService;
import com.karach.compositetask.service.impl.TextServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    private static final String PATH_TO_TEXT = "src/main/resources/text.txt";

    public static void main(String[] args) {
        try {
            Path path = Paths.get(PATH_TO_TEXT);

            TextReader textReader = new TextReaderImpl();
            String text = textReader.read(path);

            TextParser symbolTextParser = new SymbolTextParser();
            TextParser sentenceTextParser = new SentenceTextParser(symbolTextParser);

            TextComponent textComponent = sentenceTextParser.parse(text);

            TextService textService = new TextServiceImpl(textReader, sentenceTextParser);

            if (textComponent != null) {
                textService.sortParagraphsBySentenceCount(textComponent);
                TextComponent longestWordSentence = textService.findSentencesWithLongestWord(textComponent);
                textService.removeSentencesWithWordCountLessThan(textComponent, 5);
                int wordOccurrences = textService.countOccurrencesOfWordsIgnoreCase(textComponent, "Lorem");
                textService.countVowelsAndConsonantsInSentence(textComponent);

                String composedText = textService.composeText(textComponent);
                System.out.println("Composed Text: " + composedText);
                logger.info("Composed Text: {}", composedText);
                logger.info ("Text composition completed successfully.");
            }
        } catch (CompositeException e) {
            logger.error("A CompositeException occurred: " + e.getMessage());
        }
    }
}