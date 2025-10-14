package com.ua.rush.CaesarCipher;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.SplittableRandom;

public class Runner {
    static CaesarCipher caesarCipher = new CaesarCipher();
    /* Coding start method */
    public static String codingInformation (String command, Path path, int key) {
        return caesarCipher.codingInformation(command, path, key);
    }
    /* Brute force method with read verification */
    public static String[] bruteForceFile (Path path) {
        return caesarCipher.BRUTE_FORCE(path);
    }
    /* Brute force method with verification via analytical file */
    public static String[] bruteForceFile (Path path, Path filePathForStaticAnalysis) {
        return caesarCipher.BRUTE_FORCE(path, filePathForStaticAnalysis);
    }
    /* Method run via arguments */
    public static void consoleStart (String[] args) {
        String cmd = args[0].trim().toUpperCase();

        String newText = "";
        String[] arrBrute = null;

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

                    newText = caesarCipher.codingInformation(cmd, path, key);
                    Path newFileName = FileService.createNameFile(path, cmd);
                    FileService.writeFile(newFileName, newText);
                }
                case "BRUTE_FORCE" -> {
                    if (args.length < 2) {
                        System.out.println("Usage: BRUTE_FORCE <path> [verifyPath]");
                        return;
                    } else if (args.length == 2) {
                        Path path = Path.of(args[1]);
                        arrBrute = caesarCipher.BRUTE_FORCE(path);
                    } else {
                        Path path = Path.of(args[1]);
                        Path verifyPath = Path.of(args[2]);
                        arrBrute = caesarCipher.BRUTE_FORCE(path, verifyPath);
                    }

                    cmd += " → " + arrBrute[1];
                    Path newFileName = FileService.createNameFile(Path.of(args[1]), cmd);
                    FileService.writeFile(newFileName, arrBrute[0]);
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
