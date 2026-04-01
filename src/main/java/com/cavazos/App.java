package com.cavazos;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.Scanner;
import org.json.simple.*;

public class App {

    private static final String line = "--------------------------------------------------------------";
    private static final Random rand = new Random();
    private static Deque<String> undoStack = new ArrayDeque<>();
    private static Deque<String> redoStack = new ArrayDeque<>();

    // Display the menu
    public static void printMenu() {
        System.out.println(line);
        System.out.println("General Cavazos Commander App");
        System.out.println(line);
        System.out.println("i\tIssue a command");
        System.out.println("l\tList all of the commands");
        System.out.println("u\tUndo the last command that was issued");
        System.out.println("r\tRedo the last command that was issued");
        System.out.println("m\tShow menu");
        System.out.println("q\tQuit");
        System.out.println(line);
    }

    // Issue a random command
    public static void issueCommand(String[] commandArray) {
        int randIndex = rand.nextInt(commandArray.length);
        String command = commandArray[randIndex];
        undoStack.push(command);
        redoStack.clear();
        System.out.println("Issued: " + command);
    }

    // Undo the last issued command
    public static void undoCommand() {
        if (undoStack.isEmpty()) {
            System.out.println("No commands to undo.");
        } else {
            String undone = undoStack.pop();
            redoStack.push(undone);
            System.out.println("Undone: " + undone);
        }
    }

    // Redo the last undone command
    public static void redoCommand() {
        if (redoStack.isEmpty()) {
            System.out.println("No commands to redo.");
        } else {
            String redone = redoStack.pop();
            undoStack.push(redone);
            System.out.println("Redone: " + redone);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load commands from JSON
        JSONArray commandJSONArray = JSONFile.readArray("commands.json");
        String[] commandArray = Cavazos.getCommandArray(commandJSONArray);

        boolean userQuit = false;
        String input;

        // Show menu once at startup
        printMenu();

        // Menu loop
        while (!userQuit) {
            System.out.print("Enter a command or 'm' to show menu: ");
            input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "i":
                    issueCommand(commandArray);
                    break;
                case "l":
                    Cavazos.print(commandArray);
                    break;
                case "u":
                    undoCommand();
                    break;
                case "r":
                    redoCommand();
                    break;
                case "m":
                    printMenu();
                    break;
                case "q":
                    System.out.println("Goodbye, General!");
                    userQuit = true;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
            System.out.println(line);
        }

        scanner.close();
    }
}
