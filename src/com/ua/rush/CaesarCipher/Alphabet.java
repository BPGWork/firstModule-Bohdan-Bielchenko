package com.ua.rush.CaesarCipher;

/* This Enum stores different alphabets for encoding and decoding */
public enum Alphabet {
    /* English alphabet */
    ENG(new String[]{
            "a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u",
            "v", "w", "x", "y", "z"}),
    /* Ukrainian alphabet */
    UKR(new String[]{
            "а", "б", "в", "г", "ґ", "д", "е", "є",
            "ж", "з", "и", "і", "ї", "й", "к", "л",
            "м", "н", "о", "п", "р", "с", "т", "у",
            "ф", "х", "ц", "ч", "ш", "щ", "ь", "ю", "я"}),
    /* symbols */
    SYMBOL(new String[]{
        ".", ",", "«", "»", "\"", "'", ":", "!", "?", " "
    });

    private final String[] elements;
    private Alphabet (String[] strings) {
        this.elements = strings;
    }

    /* Checking which alphabet an element is from */
    protected static String detectAlphabet(char c) {
        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
            return "ENG";
        } else if ((c >= 'А' && c <= 'Я') || (c >= 'а' && c <= 'я') ||
                c == 'Ґ' || c == 'ґ' || c == 'Є' || c == 'є' ||
                c == 'І' || c == 'і' || c == 'Ї' || c == 'ї') {
            return "UKR";
        } else if (c == '.' || c == ',' || c == '«' || c == '»' ||
                c == '"'|| c == '\'' || c == ':' || c == '!' ||
                c == '?' || c == ' ') {
            return "SYMBOL";
        }else {
            return "UNKNOWN";
        }
    }

    /* Alphabet size */
    protected int alphabetLength () {
        return elements.length;
    }
    /* Getting an element from the alphabet */
    protected String getElemet (int index) {
        return elements[index];
    }
}
