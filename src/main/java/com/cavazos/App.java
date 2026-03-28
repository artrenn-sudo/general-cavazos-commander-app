package com.cavazos;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import org.json.simple.*;

public class App {

    // Display the menu
    public static void printMenu() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("General Cavazos Commander App");
        System.out.println("--------------------------------------------------------------");
        System.out.println("i\tIssue a command");
        System.out.println("l\tList all of the commands");
        System.out.println("u\tUndo the last command that was issued");
        System.out.println("r\tRedo the last command that was issued");
        System.out.println("q\tQuit");
        System.out.println("--------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Load commands from JSON
        JSONArray commandJSONArray = JSONFile.readArray("commands.json");
        String[] commandArray = Cavazos.getCommandArray(commandJSONArray);

        Random rand = new Random();
        Stack<String> undoStack = new Stack<>();
        Stack<String> redoStack = new Stack<>();

        while (running) {
            printMenu();
            System.out.print("Enter a command: ");
            String input = scanner.nextLine().trim().toLowerCase();

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
                case "q":
                    running = false;
                    System.out.println("Goodbye, General!");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
