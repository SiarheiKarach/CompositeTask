package com.karach.compositetask.reader.impl;

import com.karach.compositetask.exception.CompositeException;
import com.karach.compositetask.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextReaderImpl implements TextReader {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public String readText(String filePath) throws CompositeException {
        return readText(filePath, Charset.defaultCharset());
    }

    @Override
    public String readText(String filePath, Charset charset) {
        try {
            return Files.readString(Path.of(filePath), charset);
        } catch (IOException e) {
            logger.error("Error reading text from file", e);
            return null;
        }
    }
}