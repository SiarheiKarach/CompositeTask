package com.karach.compositetask.reader;

import com.karach.compositetask.exception.CompositeException;

import java.nio.charset.Charset;

public interface TextReader {
    String readText(String filePath) throws CompositeException;

    String readText(String filePath, Charset charset) throws CompositeException;
}