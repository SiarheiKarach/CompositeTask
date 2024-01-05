package com.karach.compositetask.model;

public enum TextComponentType {
    TEXT,
    PARAGRAPH("\\t| {4}", // The start of a paragraph with tab or 4 spaces
            "\\n"),        // The end of a paragraph with a new line
    SENTENCE("[A-Z][^.!?]*[.!?]", // A sentence starting with an uppercase letter and ending with .!? and can have any characters in between except .!?
            "\\s+"),                // The space between sentences
    LEXEME("\\b[^\\s]+\\b",  // A word, excluding whitespace and punctuations
            "\\s+"),           // The space between words
    WORD("\\b\\w+\\b"),       // A word with word boundaries
    SYMBOL("."),              // Any single character
    COMPOSITE;

    private String[] regexParts;

    TextComponentType(String... regexParts) {
        this.regexParts = regexParts;
    }

    public String getRegex() {
        return String.join("", regexParts);
    }
}