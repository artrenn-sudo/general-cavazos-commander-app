package com.cavazos;

import java.util.Random;
import org.json.simple.*;

public class CavazosExample {

  public static void main(String[] args) {
    String fileName = "commands.json";

    // Read commands from JSON
    JSONArray commandJSONArray = JSONFile.readArray(fileName);
    String[] commandArray = getCommandArray(commandJSONArray);

    // Print list of all commands
    System.out.println("----- List of all commands -----");
    print(commandArray);

    System.out.println("----- Issuing 5 random commands from General Cavazos -----");
    randomCommand(commandArray, 5);
  }

  // Randomly issue commands from General Cavazos
  public static void randomCommand(String[] commandArray, int numCommand) {
    Random rand = new Random();
    System.out.printf("Number\tCommand\n");
    System.out.printf("------\t---------------\n");
    for (int i = 0; i < numCommand; i++) {
      int randIndex = rand.nextInt(commandArray.length);
      System.out.printf("%04d\t%s\n", i, commandArray[randIndex]);
    }
  }

  // Print command array
  public static void print(String[] commandArray) {
    System.out.printf("Number\tCommand\n");
    System.out.printf("------\t---------------\n");
    for (int i = 0; i < commandArray.length; i++) {
      System.out.printf("%04d\t%s\n", i, commandArray[i]);
    }
  }

  // Convert JSONArray to String array
  public static String[] getCommandArray(JSONArray commandArray) {
    String[] arr = new String[commandArray.size()];
    for (int i = 0; i < commandArray.size(); i++) {
      String command = commandArray.get(i).toString();
      arr[i] = command;
    }
    return arr;
  }
}
