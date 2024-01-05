package com.karach.compositetask.parser.impl;

import com.karach.compositetask.model.TextComponent;
import com.karach.compositetask.model.TextComponentType;
import com.karach.compositetask.model.TextComposite;
import com.karach.compositetask.model.TextSymbol;
import com.karach.compositetask.parser.TextParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeTextParser implements TextParser {
    private TextParser nextParser;

    public LexemeTextParser(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite lexemeComposite = new TextComposite(TextComponentType.LEXEME);
        List<TextComponent> components = parseComponents(text);
        lexemeComposite.setChildren(components);
        lexemeComposite.setType(TextComponentType.LEXEME);
        return lexemeComposite;
    }

    @Override
    public List<TextComponent> parseComponents(String text) {
        List<TextComponent> components = new ArrayList<>();
        String regex = TextComponentType.LEXEME.getRegex();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String match = matcher.group();
            TextComponent component = nextParser != null ? nextParser.parse(match) : new TextSymbol(match.charAt(0), TextComponentType.SYMBOL);
            components.add(component);
        }

        return components;
    }
    @Override
    public void setType(TextComponent component, TextComponentType type) {

    }

    @Override
    public TextComponentType getType(TextComponent component) {
        return null;
    }

    @Override
    public List<TextComponent> getChildren(TextComponent component) {
        return null;
    }

    @Override
    public void setChildren(TextComponent component, List<TextComponent> children) {

    }

    @Override
    public String toString(TextComponent component) {
        return null;
    }
}