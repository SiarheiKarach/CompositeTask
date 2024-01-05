package com.karach.compositetask.parser;

import com.karach.compositetask.model.TextComponent;
import com.karach.compositetask.model.TextComponentType;

import java.util.List;

public interface TextParser {
  TextComponent parse(String text);

  List<TextComponent> parseComponents(String text);

  void setType(TextComponent component, TextComponentType type);

  TextComponentType getType(TextComponent component);

  List<TextComponent> getChildren(TextComponent component);

  void setChildren(TextComponent component, List<TextComponent> children);

  String toString(TextComponent component);
}