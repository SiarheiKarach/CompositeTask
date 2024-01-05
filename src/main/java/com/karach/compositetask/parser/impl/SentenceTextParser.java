package com.karach.compositetask.parser.impl;

import com.karach.compositetask.model.TextComponent;
import com.karach.compositetask.model.TextComponentType;
import com.karach.compositetask.model.TextComposite;
import com.karach.compositetask.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceTextParser implements TextParser {
    private static final Logger logger = LogManager.getLogger();
    private TextParser nextParser;

    public SentenceTextParser(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite sentenceComposite = new TextComposite(TextComponentType.SENTENCE);
        List<TextComponent> components = parseComponents(text);
        sentenceComposite.setChildren(components);
        sentenceComposite.setType(TextComponentType.SENTENCE);
        logger.info("Parsed sentence: {}", sentenceComposite);
        return sentenceComposite;
    }

    @Override
    public List<TextComponent> parseComponents(String text) {
        List<TextComponent> components = new ArrayList<>();
        String regex = TextComponentType.SENTENCE.getRegex();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String match = matcher.group();
            TextComponent component = nextParser != null ? nextParser.parse(match) : new SymbolTextParser().parse(match);
            components.add(component);
        }

        return components;
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