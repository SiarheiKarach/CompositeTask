package com.karach.compositetask.service;

import com.karach.compositetask.exception.CompositeException;
import com.karach.compositetask.model.TextComponent;

public interface TextService {

    TextComponent parseText(String filePath) throws CompositeException;

    String composeText(TextComponent textComponent);

    void sortParagraphsBySentenceCount(TextComponent textComponent);

    TextComponent findSentencesWithLongestWord(TextComponent textComponent);

    void removeSentencesWithWordCountLessThan(TextComponent textComponent, int wordCount);

    int countOccurrencesOfWordsIgnoreCase(TextComponent textComponent, String word);

    void countVowelsAndConsonantsInSentence(TextComponent textComponent);

}