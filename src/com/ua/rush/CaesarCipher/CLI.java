package com.ua.rush.CaesarCipher;

import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

/* This class interacts with user */
public class CLI {
    protected String command = null;
    protected String filePath = null;
    protected int key;

    protected CaesarCipher chipher = new CaesarCipher();

    /* Start interface */
    public void startProject() {
        Scanner scanner = new Scanner(System.in);
        boolean isWork = true;

        while (isWork) {
            System.out.println("1 -> Encode information");
            System.out.println("2 -> Decode information");
            System.out.println("3 -> BRUTE_FORCE");
            System.out.println("0 -> Exit");
            System.out.print("\nChoose what you want to do: ");

            try {
                switch (scanner.nextInt()) {
                    case 1 -> {
                        setCommand("ENCRYPT");
                        codingInfo();
                    }
                    case 2 -> {
                        setCommand("DECRYPT");
                        codingInfo();
                    }
                    case 3 -> {
                        bruteInfo();
                    }
                    case 0 -> isWork = false;
                    default -> System.out.println("Wrong choice.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("The key must be an integer.\n");
            } catch (RuntimeException e) {
                System.out.println(e + "\n");
            }
        }
    }
    /* Set command */
    protected void setCommand(String command) {
        this.command = command;
    }
    /* Encryption/Decryption by key */
    protected void codingInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path to the file: ");
        this.filePath = scanner.nextLine().trim();

        if (filePath.isEmpty()) {
            System.out.println("Path cannot be empty.\n");
            return;
        }

        System.out.print("Enter the encryption key: ");
        this.key = scanner.nextInt();

        String newText = chipher.codingInformation(command, Path.of(filePath), key);

        Path newFileName = FileService.createNameFile(Path.of(filePath), command.toUpperCase());
        FileService.writeFile(newFileName, newText);

        System.out.println(command + " file\n");
    }
    /* Brute force */
    protected void bruteInfo() {
        Scanner scanner = new Scanner(System.in);
        String[] arrBrute = null;

        System.out.print("Enter the path to the file: ");
        this.filePath = scanner.nextLine();

        System.out.print("You have a file to check: ");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("Yes")) {
            System.out.print("Enter the path to the analytical file: ");
            String analyticalFile = scanner.nextLine();

            arrBrute = chipher.BRUTE_FORCE(Path.of(filePath), Path.of(analyticalFile));
        } else if (answer.equalsIgnoreCase("No")) {
            arrBrute = chipher.BRUTE_FORCE(Path.of(filePath));
        }

        command += " → " + arrBrute[1];
        Path newFileName = FileService.createNameFile(Path.of(filePath), command);
        FileService.writeFile(newFileName, arrBrute[0]);

        System.out.println("BRUTE_FORCE file\n");
    }
}
