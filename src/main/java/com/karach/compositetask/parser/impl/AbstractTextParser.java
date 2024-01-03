package com.karach.compositetask.parser.impl;

import com.karach.compositetask.model.TextComponent;
import com.karach.compositetask.model.TextComponentType;
import com.karach.compositetask.model.TextComposite;
import com.karach.compositetask.parser.TextParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractTextParser implements TextParser {
    private TextParser nextParser;

    public AbstractTextParser(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract TextComponent parse(String text);

    protected List<TextComposite> parseComponents(String text, String regex) {
        List<TextComposite> components = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String match = matcher.group();
            TextComposite component = nextParser != null ? (TextComposite) nextParser.parse(match) : new TextComposite(TextComponentType.TEXT);
            components.add(component);
        }

        return components;
    }
}