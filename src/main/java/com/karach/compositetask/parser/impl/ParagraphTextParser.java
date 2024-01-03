package com.karach.compositetask.parser.impl;

import com.karach.compositetask.model.TextComponent;
import com.karach.compositetask.model.TextComponentType;
import com.karach.compositetask.model.TextComposite;
import com.karach.compositetask.parser.TextParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphTextParser implements TextParser {
    private final TextParser nextParser;

    public ParagraphTextParser(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);
        String paragraphRegex = TextComponentType.PARAGRAPH.getRegex();
        Pattern paragraphPattern = Pattern.compile(paragraphRegex);
        Matcher paragraphMatcher = paragraphPattern.matcher(text);

        while (paragraphMatcher.find()) {
            String paragraphText = paragraphMatcher.group();
            TextComponent sentenceComponent = nextParser.parse(paragraphText);
            paragraphComposite.add(sentenceComponent);
        }

        return paragraphComposite;
    }
}