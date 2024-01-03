package com.karach.compositetask.service.impl;

import com.karach.compositetask.exception.CompositeException;
import com.karach.compositetask.model.TextComponent;
import com.karach.compositetask.model.TextComponentType;
import com.karach.compositetask.model.TextComposite;
import com.karach.compositetask.model.TextSymbol;
import com.karach.compositetask.parser.TextParser;
import com.karach.compositetask.reader.TextReader;
import com.karach.compositetask.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private final TextReader textReader;
    private final TextParser textParser;
    private static final Logger logger = LogManager.getLogger();

    public TextServiceImpl(TextReader textReader, TextParser textParser) {
        this.textReader = textReader;
        this.textParser = textParser;
    }

    @Override
    public TextComponent parseText(String filePath) throws CompositeException {
        String text = textReader.readText(filePath);
        return text != null ? textParser.parse(text) : null;
    }

    @Override
    public String composeText(TextComponent textComponent) {
        StringBuilder stringBuilder = new StringBuilder();
        composeTextRecursively(textComponent, stringBuilder);
        return stringBuilder.toString();
    }

    private void composeTextRecursively(TextComponent textComponent, StringBuilder stringBuilder) {
        if (textComponent.getType() == TextComponentType.SYMBOL) {
            stringBuilder.append(((TextSymbol) textComponent).getSymbol());
        } else if (textComponent.getType() == TextComponentType.COMPOSITE) {
            for (TextComponent child : ((TextComposite) textComponent).getChildren()) {
                composeTextRecursively(child, stringBuilder);
            }
        }
    }

    @Override
    public void sortParagraphsBySentenceCount(TextComponent textComponent) {
        if (textComponent.getType() == TextComponentType.COMPOSITE) {
            List<TextComponent> paragraphs = ((TextComposite) textComponent).getChildren();
            paragraphs.sort(Comparator.comparingInt(paragraph ->
                    ((TextComposite) paragraph).getChildren().size()));
        }
    }

    @Override
    public TextComponent findSentencesWithLongestWord(TextComponent textComponent) {
        if (textComponent.getType() == TextComponentType.COMPOSITE) {
            List<TextComponent> sentences = ((TextComposite) textComponent).getChildren();

            return sentences.stream()
                    .max(Comparator.comparingInt(sentence ->
                            ((TextComposite) sentence).getChildren().stream()
                                    .filter(component -> component.getType() == TextComponentType.WORD)
                                    .mapToInt(component -> ((TextComposite) component).getChildren().size())
                                    .max()
                                    .orElse(0)))
                    .orElse(null);
        }
        return null;
    }

    @Override
    public void removeSentencesWithWordCountLessThan(TextComponent textComponent, int wordCount) {
        if (textComponent.getType() == TextComponentType.COMPOSITE) {
            List<TextComponent> sentences = ((TextComposite) textComponent).getChildren();
            sentences.removeIf(sentence ->
                    ((TextComposite) sentence).getChildren().stream()
                            .filter(component -> component.getType() == TextComponentType.WORD)
                            .count() < wordCount);
        }
    }

    @Override
    public int countOccurrencesOfWordsIgnoreCase(TextComponent textComponent, String word) {
        if (textComponent.getType() == TextComponentType.COMPOSITE) {
            List<TextComponent> words = ((TextComposite) textComponent).getChildren().stream()
                    .filter(component -> component.getType() == TextComponentType.WORD)
                    .collect(Collectors.toList());

            long count = words.stream()
                    .filter(w -> ((TextComposite) w).getChildren().stream()
                            .allMatch(component ->
                                    Character.toLowerCase(((TextSymbol) component).getSymbol()) ==
                                            Character.toLowerCase(word.charAt(((TextComposite) w).getChildren().indexOf(component)))))
                    .count();

            return (int) count;
        }
        return 0;
    }

    @Override
    public void countVowelsAndConsonantsInSentence(TextComponent textComponent) {
        if (textComponent.getType() == TextComponentType.COMPOSITE) {
            List<TextComponent> sentences = ((TextComposite) textComponent).getChildren();
            for (TextComponent sentence : sentences) {
                int vowelCount = 0;
                int consonantCount = 0;
                for (TextComponent component : ((TextComposite) sentence).getChildren()) {
                    if (component.getType() == TextComponentType.SYMBOL) {
                        char symbol = ((TextSymbol) component).getSymbol();
                        if (Character.isLetter(symbol)) {
                            if ("AEIOUaeiou".indexOf(symbol) != -1) {
                                vowelCount++;
                            } else {
                                consonantCount++;
                            }
                        }
                    }
                }
                logger.info("Sentence: {}", composeText(sentence));
                logger.info("Vowels: {}", vowelCount);
                logger.info("Consonants: {}", consonantCount);
                logger.info("--------------");
            }
        }
    }
}