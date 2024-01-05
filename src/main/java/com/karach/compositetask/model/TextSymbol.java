package com.karach.compositetask.model;

import java.util.Collections;
import java.util.List;

public class TextSymbol implements TextComponent {
    private final char symbol;
    private TextComponentType type;

    public TextSymbol(char symbol, TextComponentType type) {
        this.symbol = symbol;
        this.type = type;
    }

    @Override
    public void setType(TextComponentType type) {
        this.type = type;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public void setChildren(List<TextComponent> children) {
        throw new UnsupportedOperationException("TextSymbol does not support setChildren operation.");
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("TextSymbol does not support add operation.");
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("TextSymbol does not support remove operation.");
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}