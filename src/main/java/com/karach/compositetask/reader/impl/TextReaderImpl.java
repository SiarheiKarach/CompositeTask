package com.karach.compositetask.reader.impl;
import com.karach.compositetask.exception.CompositeException;
import com.karach.compositetask.reader.TextReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextReaderImpl implements TextReader {
    @Override
    public String read(Path filePath) throws CompositeException {
        try {
            if (!Files.exists(filePath)) {
                throw new CompositeException("File not found: " + filePath);
            }

            return Files.readString(filePath);
        } catch (IOException e) {
            throw new CompositeException("Error reading text file", e);
        }
    }
}