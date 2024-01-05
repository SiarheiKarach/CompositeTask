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
    private TextParser nextParser;

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

    @Override
    public List<TextComponent> parseComponents(String text) {
        throw new UnsupportedOperationException("ParagraphTextParser does not support parseComponents method.");
    }

    @Override
    public void setType(TextComponent component, TextComponentType type) {
        if (component instanceof TextComposite) {
            ((TextComposite) component).setType(type);
        }
    }

    @Override
    public TextComponentType getType(TextComponent component) {
        if (component instanceof TextComposite) {
            return ((TextComposite) component).getType();
        }
        return null;
    }

    @Override
    public List<TextComponent> getChildren(TextComponent component) {
        if (component instanceof TextComposite) {
            return ((TextComposite) component).getChildren();
        }
        return new ArrayList<>();
    }

    @Override
    public void setChildren(TextComponent component, List<TextComponent> children) {
        if (component instanceof TextComposite) {
            ((TextComposite) component).setChildren(children);
        }
    }

    @Override
    public String toString(TextComponent component) {
        if (component instanceof TextComposite) {
            List<TextComponent> children = ((TextComposite) component).getChildren();
            StringBuilder result = new StringBuilder();
            for (TextComponent child : children) {
                result.append(child.toString());
            }
            return result.toString();
        } else {
            return "";
        }
    }
}