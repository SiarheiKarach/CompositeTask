package com.karach.compositetask.parser;

import com.karach.compositetask.model.TextComponent;

public interface TextParser {
    TextComponent parse(String text);
}