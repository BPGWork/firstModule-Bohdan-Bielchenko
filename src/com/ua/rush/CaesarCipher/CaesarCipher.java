package com.ua.rush.CaesarCipher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/* This class is used for ENCRYPT and DECRYPT text using a key */
class CaesarCipher {
    /* File encryption/decryption by key */
    protected void codingInformation (String command, Path path, int key) {
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

        Path newFileName = FileService.createNameFile(path, command.toUpperCase());
        FileService.writeFile(newFileName, convertWithChar(text, encryptText));
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
                if (result >= alphabet.alphabetLength()) {
                    result -= alphabet.alphabetLength();
                }
                if (result < 0) {
                    result += alphabet.alphabetLength();
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
    protected void BRUTE_FORCE (Path path) {
        Scanner scanner = new Scanner(System.in);

        String command = "BRUTE_FORCE";
        String answer = "";
        int key = 1;
        boolean isWork = true;

        while (isWork) {
            codingInformation("DECRYPT", path, key);
            Path decryptFile = FileService.createNameFile(path, "DECRYPT".toUpperCase());
            String decryptInformation = FileService.readFile(decryptFile);

            System.out.println(decryptInformation + "\n");
            System.out.println("1 -> Yes");
            System.out.println("2 -> No");
            System.out.print("The text provides readable information: ");
            answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("1")) {
                FileService.removeFile(decryptFile);

                command += " → " + key;
                Path bruteFile = FileService.createNameFile(path, command.toUpperCase());
                FileService.writeFile(bruteFile, decryptInformation);

                isWork = false;
            } else if (answer.equalsIgnoreCase("2")) {
                key++;
            }
        }
    }
    /* Brute force file with verification */
    protected void BRUTE_FORCE (Path path, Path filePathForStaticAnalysis) {
        String analyticalInformation = FileService.readFile(filePathForStaticAnalysis);
        String command = "BRUTE_FORCE";
        int key = 1;
        boolean isWork = true;

        while (isWork) {
            codingInformation("DECRYPT", path, key);
            Path decryptFile = FileService.createNameFile(path, "DECRYPT".toUpperCase());
            String decryptInformation = FileService.readFile(decryptFile);

            if (analyticalInformation.equals(decryptInformation)) {
                FileService.removeFile(decryptFile);

                command += " → " + key;
                Path bruteFile = FileService.createNameFile(path, command.toUpperCase());
                FileService.writeFile(bruteFile, decryptInformation);

                isWork = false;
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