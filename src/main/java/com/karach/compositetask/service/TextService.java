package com.karach.compositetask.service;

import com.karach.compositetask.exception.CompositeException;
import com.karach.compositetask.model.TextComponent;
import com.karach.compositetask.parser.TextParser;
import com.karach.compositetask.reader.TextReader;

import java.io.IOException;

public interface TextService {

    String composeText(TextComponent textComponent);

    void sortParagraphsBySentenceCount(TextComponent textComponent);

    TextComponent findSentencesWithLongestWord(TextComponent textComponent);

    void removeSentencesWithWordCountLessThan(TextComponent textComponent, int wordCount);

    int countOccurrencesOfWordsIgnoreCase(TextComponent textComponent, String word);

    void countVowelsAndConsonantsInSentence(TextComponent textComponent);

    }