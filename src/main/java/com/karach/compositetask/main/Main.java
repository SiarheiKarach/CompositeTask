package com.karach.compositetask.main;

import com.karach.compositetask.exception.CompositeException;
import com.karach.compositetask.model.TextComponent;
import com.karach.compositetask.reader.TextReader;
import com.karach.compositetask.reader.impl.TextReaderImpl;
import com.karach.compositetask.parser.TextParser;
import com.karach.compositetask.service.TextService;
import com.karach.compositetask.service.impl.TextServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    private static final String PATH_TO_TEXT = "src/main/resources/Text.txt".replace("\uFEFF", "").trim();
    public static void main(String[] args) throws CompositeException{
        TextReader reader = new TextReaderImpl();
        String text = reader.readText(PATH_TO_TEXT);

        if (text != null) {
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
        }
    }
}