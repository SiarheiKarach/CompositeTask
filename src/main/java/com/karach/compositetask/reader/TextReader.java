package com.karach.compositetask.reader;

import com.karach.compositetask.exception.CompositeException;
import com.karach.compositetask.service.TextService;

import java.nio.file.Path;

public interface TextReader {
    String read(Path filePath) throws CompositeException;
}