package com.ikasako.sudachi.perftest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.worksap.nlp.sudachi.Dictionary;
import com.worksap.nlp.sudachi.DictionaryFactory;
import com.worksap.nlp.sudachi.Tokenizer;

public class SudachiPerfTest {
    private Tokenizer tokenizer;
    public static void main(String[] args) throws IOException {
        SudachiPerfTest instance = new SudachiPerfTest();
        String plainText = Files.readString(Path.of("app/src/main/resources", "sentences.txt"));
        instance.tokenizeSentences(plainText);
    }

    SudachiPerfTest() throws IOException {
        String settings = null;
        boolean mergeSettings = false;
        String resourcesDirectory = "app/src/main/resources";
        try (Dictionary dict = new DictionaryFactory().create(resourcesDirectory, settings, mergeSettings)) {
            this.tokenizer = dict.create();
        }
    }

    public void tokenizeSentences(String str) {
        tokenizer.tokenizeSentences(str);
    }
}
