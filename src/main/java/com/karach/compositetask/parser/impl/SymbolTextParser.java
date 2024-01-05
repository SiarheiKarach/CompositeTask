package com.karach.compositetask.parser.impl;

import com.karach.compositetask.model.TextComponent;
import com.karach.compositetask.model.TextComponentType;
import com.karach.compositetask.model.TextSymbol;
import com.karach.compositetask.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SymbolTextParser implements TextParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public TextComponent parse(String text) {
        if (!text.isEmpty()) {
            char symbol = text.charAt(0);
            logger.info("Parsed symbol: {}", symbol);
            return new TextSymbol(symbol, TextComponentType.SYMBOL);
        } else {
            throw new IllegalArgumentException("Cannot parse an empty text in SymbolTextParser.");
        }
    }

    @Override
    public List<TextComponent> parseComponents(String text) {
        throw new UnsupportedOperationException("SymbolTextParser does not support parseComponents method.");
    }

    @Override
    public void setType(TextComponent component, TextComponentType type) {
        if (component instanceof TextSymbol) {
            ((TextSymbol) component).setType(type);
        }
    }

    @Override
    public TextComponentType getType(TextComponent component) {
        if (component instanceof TextSymbol) {
            return ((TextSymbol) component).getType();
        }
        return null;
    }

    @Override
    public List<TextComponent> getChildren(TextComponent component) {
        throw new UnsupportedOperationException("SymbolTextParser does not have children.");
    }

    @Override
    public void setChildren(TextComponent component, List<TextComponent> children) {
        throw new UnsupportedOperationException("SymbolTextParser does not have children.");
    }

    @Override
    public String toString(TextComponent component) {
        if (component instanceof TextSymbol) {
            return String.valueOf(((TextSymbol) component).getSymbol());
        }
        return "";
    }
}