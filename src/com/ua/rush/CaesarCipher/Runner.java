package com.ua.rush.CaesarCipher;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.SplittableRandom;

public class Runner {
    /* Interface launch method */
    public static void consoleInterfaceStart () {
        CLI user = new CLI();
        user.startProject();
    }
    /* Method run via arguments */
    public static void consoleStart (String[] args) {
        CaesarCipher caesarCipher = new CaesarCipher();
        String cmd = args[0].trim().toUpperCase();

        if (!("ENCRYPT".equals(cmd)) &&
            !("DECRYPT".equals(cmd)) && !("BRUTE_FORCE".equals(cmd)) && !("HELP".equals(cmd))) {
            System.out.println("Wrong command!");
            System.out.println("Enter as the first argument: HELP");
            System.out.println("To view commands.\n");
        }

        if ("HELP".equals(cmd)) {
            printHelp();
        }

        try {
            switch (cmd) {
                case "ENCRYPT", "DECRYPT" -> {
                    if (args.length < 3) {
                        System.out.println("Usage: " + cmd + " <path> <key>");
                        return;
                    }

                    Path path = Path.of(args[1]);
                    int key = Integer.parseInt(args[2]);
                    caesarCipher.codingInformation(cmd, path, key);
                }
                case "BRUTE_FORCE" -> {
                    if (args.length < 2) {
                        System.out.println("Usage: BRUTE_FORCE <path> [verifyPath]");
                        return;
                    } else if (args.length == 2) {
                        Path path = Path.of(args[1]);
                        caesarCipher.BRUTE_FORCE(path);
                    } else {
                        Path path = Path.of(args[1]);
                        Path verifyPath = Path.of(args[2]);
                        caesarCipher.BRUTE_FORCE(path, verifyPath);
                    }
                }
            }
        } catch (NumberFormatException e) { // Key verification
            System.err.println("Key must be an integer. Provided: " + (args.length > 2 ? args[2] : "<missing>"));
        } catch (InvalidPathException e) { // Path Validation Check
            System.err.println("Invalid path: " + e.getInput());
        } catch (Throwable t) { // other errors
            System.err.println("Error: " + t.getMessage());
            t.printStackTrace();
        }
    }
    /* Documentation */
    private static void printHelp() {
        System.out.println("ENCRYPT -> Encode information ( ENCRYPT <path> <key> )");
        System.out.println("DECRYPT -> Decode information ( DECRYPT <path> <key> )");
        System.out.println("BRUTE_FORCE -> Searches for the key and decrypts the information");
        System.out.println("Path to the file for brute ( BRUTE_FORCE <path> [verifyPath]");
        System.out.println("If you don't have an analytical file");
        System.out.println("The first argument is the file path for brute ( BRUTE_FORCE <path>");
        System.out.println("Launching!");
    }
}
