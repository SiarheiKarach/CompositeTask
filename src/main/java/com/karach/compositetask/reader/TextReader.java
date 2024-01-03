package com.karach.compositetask.reader;

import com.karach.compositetask.exception.CompositeException;

public interface TextReader {
    String readText(String filePath) throws CompositeException;
}