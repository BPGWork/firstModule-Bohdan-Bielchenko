package com.ua.rush.CaesarCipher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/* This class is used for read and write files */
class FileService {
    /* This method read file */
    protected static String readFile (Path path) {
        String textArray = null;
        try {
            textArray = Files.readString(path, StandardCharsets.UTF_8);
        } catch (AccessDeniedException e) {
            throw new RuntimeException("Field not found");
        } catch (NoSuchFileException e) {
            throw new RuntimeException("Field not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return textArray;
    }

    /* This method write file */
    protected static void writeFile (Path path, String text) {
        try {
            Files.writeString(path, text, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* This method create new name for file */
    protected static Path createNameFile (Path path, String transcription) {
        Path parentFile = path.getParent();
        String oldFileName = path.getFileName().toString();
        String newFileName = "";

        String[] splitOldFileName = oldFileName.split("[\\[\\].]+");
        newFileName = splitOldFileName[0] +  "[" + transcription + "]." + splitOldFileName[splitOldFileName.length - 1];

        return parentFile.resolve(newFileName);
    }
    /* Delete File */
    protected static void removeFile (Path path) {
        try  {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
