import java.util.Scanner;

import TelecomExceptions.RecordAlreadyExists;
import TelecomExceptions.RecordNotFound;

public class CLI {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("\nWelcome, officer!");
        displayMenu();

        while (true) {
            System.out.println("What would you like to do?");
            String input = scanner.nextLine();
            switch (input) {
                case "view records":
                    System.out.println(Records.viewRecords());
                    break;
                case "search": {
                    String number = getUserInput("phone number");
                    try {
                        System.out.println(
                                Records.searchRecord(number)
                        );
                    } catch (RecordNotFound e) {
                        System.out.printf(e.getMessage());
                    }
                    System.out.println("\n");
                    break;
                }
                case "view payments": {
                    String number = getUserInput("phone number");
                    try {
                        System.out.println(
                                Records.viewPayments(number)
                        );
                    } catch (RecordNotFound e) {
                        System.out.printf(e.getMessage());
                    }
                    System.out.println("\n");
                }
                case "add": {
                    String name = getUserInput("name");
                    String number = getUserInput("phone number");
                    float minutes = Float.parseFloat(
                            getUserInput("number of minutes used")
                    );
                    try {
                        Records.addRecord(name, number, minutes);
                    } catch (RecordAlreadyExists e) {
                        System.out.printf(e.getMessage());
                    }
                    System.out.println("\n");
                }
                case "modify": {
                    String number = getUserInput("phone number");
                    float minutes = Float.parseFloat(
                            getUserInput("number of minutes used")
                    );
                    try {
                        Records.modifyRecord(number, minutes);
                    } catch (RecordNotFound e) {
                        System.out.printf(e.getMessage());
                    }
                    System.out.println("\n");
                }
                case "delete": {
                    String number = getUserInput("phone number");
                    try {
                        Records.deleteRecord(number);
                    } catch (RecordNotFound e) {
                        System.out.printf(e.getMessage());
                    }
                    System.out.println("\n");
                }
                case "exit":
                    System.out.println("Goodbye, officer!");
                    return;
                default:
                    System.out.println("Command not found");
                    System.out.println("Type \"Help\" for a list of available commands");
                    break;
            }
        }
    }
    private static final String[] Ops = {
            "view records",
            "search",
            "view payments",
            "add",
            "modify",
            "delete",
            "exit"
    };
    private static void displayMenu() {
        System.out.printf("TELEx CLI v%s\n", Main.version);
        System.out.printf("Logged in as: %s\n", Main.user);
        System.out.println("Available commands:");
        for (String op: Ops) {
            System.out.println(op);
        }
    }
    private static String getUserInput(String name) {
        System.out.printf("Provide the %s: ", name);
        String number = scanner.nextLine();
        System.out.println();
        return number;
    }
}
