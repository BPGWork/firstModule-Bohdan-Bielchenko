package com.ua.rush.CaesarCipher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/* This class is used for ENCRYPT and DECRYPT text using a key */
class CaesarCipher {
    /* File encryption/decryption by key */
    protected String codingInformation (String command, Path path, int key) {
        String text = FileService.readFile(path);
        char[] charText = convertWithString(text);
        char element;

        char[] encryptText = new char[charText.length];

        for (int i = 0; i < charText.length; i++) {
            element = charText[i];
            switch (Alphabet.detectAlphabet(element)) {
                case "ENG" -> encryptText[i] = codingElement(element, command, key, Alphabet.ENG);
                case "UKR" -> encryptText[i] = codingElement(element, command, key, Alphabet.UKR);
                case  "SYMBOL" -> encryptText[i] = codingElement(element, command, key, Alphabet.SYMBOL);
                default -> encryptText[i] = element;
            }
        }

        return convertWithChar(text, encryptText);
    }
    /* Encrypt/Decrypt an element by key */
    private char codingElement (char element, String command, int key, Alphabet alphabet) {
        String symbol = "" + element;
        String encryptSymbol = null;

        int result = 0;
        for (int i = 0; i < alphabet.alphabetLength(); i++) {
            if (alphabet.getElemet(i).equalsIgnoreCase(symbol)) {
                if (command.equalsIgnoreCase("DECRYPT")) {
                    key *= -1;
                }

                result += i + key;
                while (result < 0 || result >= alphabet.alphabetLength()) {
                    if (result >= alphabet.alphabetLength()) {
                        result -= alphabet.alphabetLength();
                    }
                    if (result < 0) {
                        result += alphabet.alphabetLength();
                    }
                }

                encryptSymbol = alphabet.getElemet(result);

                if (!(alphabet.getElemet(i).equals(symbol))) {
                    encryptSymbol = encryptSymbol.toUpperCase();
                }

                break;
            }
        }

        return encryptSymbol.charAt(0);
    }
    /* Brute force a file without verification */
    protected String[] BRUTE_FORCE (Path path) {
        Scanner scanner = new Scanner(System.in);

        String decryptInformation = "";
        String answer = "";
        int key = 1;

        while (true) {
            decryptInformation = codingInformation("DECRYPT", path, key);

            System.out.println(decryptInformation + "\n");
            System.out.println("1 -> Yes");
            System.out.println("2 -> No");
            System.out.print("The text provides readable information: ");
            answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("1")) {
                String[] arrBrute = new String[] {decryptInformation, "" + key};
                return arrBrute;
            } else if (answer.equalsIgnoreCase("2")) {
                key++;
            }
        }
    }
    /* Brute force file with verification */
    protected String[] BRUTE_FORCE (Path path, Path filePathForStaticAnalysis) {
        String analyticalInformation = FileService.readFile(filePathForStaticAnalysis);
        String decryptInformation = "";
        int key = 1;

        while (true) {
            decryptInformation = codingInformation("DECRYPT", path, key);
            if (analyticalInformation.equals(decryptInformation)) {
                String[] arrBrute = new String[]{decryptInformation, "" + key};
                return arrBrute;
            } else {
                key++;
            }
        }
    }
    /* Converting from String Line to Char element */
    private char[] convertWithString (String text) {
        return text.toCharArray();
    }
    /* Converting from Char element to String Line */
    private String convertWithChar (String text, char[] codingElement) {
        String s = "";
        for (int i = 0; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case '\n' -> s += "\n";
                default -> s += codingElement[i];
            }
        }

        return s;
    }
}