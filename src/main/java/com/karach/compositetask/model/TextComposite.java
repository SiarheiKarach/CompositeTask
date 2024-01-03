package com.karach.compositetask.model;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private List<TextComponent> children;
    private TextComponentType type;

    public TextComposite(TextComponentType type) {
        this.type = type;
        this.children = new ArrayList<>();
    }

    @Override
    public void add(TextComponent component) {
        children.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        children.remove(component);
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
        return children;
    }

    @Override
    public void setChildren(List<TextComponent> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent child : children) {
            stringBuilder.append(child.toString());
        }
        return stringBuilder.toString();
    }
}