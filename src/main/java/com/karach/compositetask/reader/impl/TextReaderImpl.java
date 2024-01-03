package com.karach.compositetask.reader.impl;

import com.karach.compositetask.exception.CompositeException;
import com.karach.compositetask.reader.TextReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class TextReaderImpl implements TextReader {
    @Override
    public String readText(String filePath) throws CompositeException {
        try {
            Path path = Path.of(filePath);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            // Remove BOM if present in the first line
            if (!lines.isEmpty() && lines.get(0).startsWith("\uFEFF")) {
                lines.set(0, lines.get(0).substring(1));
            }

            return lines.stream().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new CompositeException("Error reading the text file: " + filePath, e);
        }
    }
}