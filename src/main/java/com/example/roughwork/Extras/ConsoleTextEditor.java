package com.example.roughwork.Extras;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleTextEditor {

    private File outputFile;

    public ConsoleTextEditor(File outputFile) {
        this.outputFile = outputFile;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Console Text Editor");
        System.out.println("Enter your text. Type 'exit' to save and exit.");

        StringBuilder text = new StringBuilder();

        try {
            String line;
            while (true) {
                line = scanner.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    saveAndExit(text.toString());
                    break;
                }
                text.append(line).append("\n");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while processing input.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private void saveAndExit(String text) {
        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            // Add a line break at the beginning to save the first line
            String textWithLineBreak = "\n" + text;
            fileWriter.write(textWithLineBreak);
            System.out.println("Text saved to '" + outputFile.getName() + "'");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the text.");
            e.printStackTrace();
        }

        try (FileReader fileReader = new FileReader(outputFile)) {
            System.out.println("Content of the saved file:");
            int data;
            while ((data = fileReader.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the saved file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleTextEditor textEditor = new ConsoleTextEditor(new File("edited_text.txt"));
        textEditor.start();
    }
}
