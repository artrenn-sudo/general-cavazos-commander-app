package com.cavazos;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.Scanner;
import org.json.simple.*;

public class App {

    private static final String LINE = "--------------------------------------------------------------";
    // Display the menu
    public static void printMenu() {
        System.out.println(LINE);
        System.out.println("General Cavazos Commander App");
        System.out.println(LINE);
        System.out.println("i\tIssue a command");
        System.out.println("l\tList all of the commands");
        System.out.println("u\tUndo the last command that was issued");
        System.out.println("r\tRedo the last command that was issued");
        System.out.println("m\tShow menu");
        System.out.println("q\tQuit");
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load commands from JSON
        JSONArray commandJSONArray = JSONFile.readArray("commands.json");
        String[] commandArray = Cavazos.getCommandArray(commandJSONArray);

        Random rand = new Random();
        Deque<String> undoStack = new ArrayDeque<>();
        Deque<String> redoStack = new ArrayDeque<>();

        String input;

        // Show menu once at startup
        printMenu();

        // Menu loop: exits when user enters 'q'
        do {
            System.out.print("Enter a command or 'm' to show menu: ");
            input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "i":
                    int randIndex = rand.nextInt(commandArray.length);
                    String command = commandArray[randIndex];
                    undoStack.push(command);
                    redoStack.clear();
                    System.out.println("Issued: " + command);
                    break;
                case "l":
                    Cavazos.print(commandArray);
                    break;
                case "u":
                    if (undoStack.isEmpty()) {
                        System.out.println("No commands to undo.");
                    } else {
                        String undone = undoStack.pop();
                        redoStack.push(undone);
                        System.out.println("Undone: " + undone);
                    }
                    break;
                case "r":
                    if (redoStack.isEmpty()) {
                        System.out.println("No commands to redo.");
                    } else {
                        String redone = redoStack.pop();
                        undoStack.push(redone);
                        System.out.println("Redone: " + redone);
                    }
                    break;
                case "m":
                    printMenu();
                    break;
                case "q":
                    System.out.println("Goodbye, General!");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
            System.out.println(LINE);
        } while (!input.equals("q"));

        scanner.close();
    }
}
