package com.ikasako.sudachi.perftest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.worksap.nlp.sudachi.Dictionary;
import com.worksap.nlp.sudachi.DictionaryFactory;
import com.worksap.nlp.sudachi.Tokenizer;

public class SudachiPerfTest {
    private Tokenizer tokenizer;
    private Dictionary dict;
    public static void main(String[] args) throws IOException {
        SudachiPerfTest instance = new SudachiPerfTest();
        String plainText = Files.readString(Path.of("app/src/main/resources", "sentences.txt"));

        System.out.println("plain text");
        instance.tokenizeSentences(plainText, 100);

        String spaceStr = StringUtil.replaceOddChar(plainText, ' ');
        System.out.println("space separated text. length:" + spaceStr.length());
        instance.tokenizeSentences(spaceStr, 100);

        spaceStr = StringUtil.amplification(spaceStr, 10);
        System.out.println("space separated text length:" + spaceStr.length());
        instance.tokenizeSentences(spaceStr, 100);

        String newLineStr = StringUtil.replaceOddChar(plainText, '\n');
        System.out.println("newline separated text length:" + newLineStr.length());
        instance.tokenizeSentences(newLineStr, 100);

        newLineStr = StringUtil.amplification(newLineStr, 10);
        System.out.println("newline separated text length:" + newLineStr.length());
        instance.tokenizeSentences(newLineStr, 100);
        
        instance.close();
    }

    SudachiPerfTest() throws IOException {
        String settings = null;
        boolean mergeSettings = false;
        String resourcesDirectory = "app/src/main/resources";
        this.dict = new DictionaryFactory().create(resourcesDirectory, settings, mergeSettings);
        this.tokenizer = dict.create();
    }

    public void tokenizeSentences(String str, int times) {
        long start = System.currentTimeMillis();
        for (int i=0; i< times; i++) {
            tokenizer.tokenizeSentences(str);
        }
        System.out.println(System.currentTimeMillis() - start + "ms");
        System.out.println("----------------------");
    }

    public void close() throws IOException {
        this.dict.close();
    }
}
