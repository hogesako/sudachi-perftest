package com.ikasako.sudachi.perftest;

public class StringUtil {
    private StringUtil(){}

    public static String replaceOddChar(String str, char c) {
        StringBuilder result = new StringBuilder();

        for (int i=0; i<str.length(); i++) {
            if (i%2 == 0) {
                result.append(str.charAt(i));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String amplification(String str, int times) {
        StringBuilder result = new StringBuilder();
        for (int i=0; i<times; i++) {
            result.append(str);
        }
        return result.toString();
    }
}
