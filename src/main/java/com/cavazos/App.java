package com.cavazos;

import java.util.Scanner;

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

        while (running) {
            printMenu();
            System.out.print("Enter a command: ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "i":
                    System.out.println("Issue command - not yet implemented");
                    break;
                case "l":
                    System.out.println("List command - not yet implemented");
                    break;
                case "u":
                    System.out.println("Undo command - not yet implemented");
                    break;
                case "r":
                    System.out.println("Redo command - not yet implemented");
                    break;
                case "q":
                    System.out.println("Quit - not yet implemented");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
