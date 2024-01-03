package com.karach.compositetask.parser.impl;

import com.karach.compositetask.model.TextComponent;
import com.karach.compositetask.model.TextComponentType;
import com.karach.compositetask.model.TextSymbol;
import com.karach.compositetask.parser.TextParser;

public class SymbolTextParser implements TextParser {
    @Override
    public TextComponent parse(String text) {
        if (!text.isEmpty()) {
            char symbol = text.charAt(0);
            return new TextSymbol(symbol, TextComponentType.SYMBOL);
        } else {
            throw new IllegalArgumentException("Cannot parse an empty text in SymbolTextParser.");
        }
    }
}