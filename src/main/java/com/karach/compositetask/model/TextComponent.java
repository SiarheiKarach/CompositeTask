package com.karach.compositetask.model;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component);

    void remove(TextComponent component);

    void setType(TextComponentType type);

    TextComponentType getType();

    List<TextComponent> getChildren();

    void setChildren(List<TextComponent> children);

    String toString();
}