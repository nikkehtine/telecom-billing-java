import java.util.Scanner;

import TelecomExceptions.RecordAlreadyExists;
import TelecomExceptions.RecordNotFound;

public class CLI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("\nWelcome, officer!");
        displayMenu();

        while (true) {
            System.out.print("\nWhat would you like to do? ");
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {

                case "view records": {
                    var records = Records.viewRecords();
                    if (!records.isEmpty()) {
                        for (Customer customer : records) printCustomer(customer);
                    } else {
                        System.out.println("There are no records yet");
                    }
                } break;

                case "search": {
                    String number = getUserInput("phone number");
                    try {
                        printCustomer(Records.searchRecord(number));
                    } catch (RecordNotFound e) {
                        System.out.println(e.getMessage());
                    }
                } break;

                case "view payments": {
                    String number = getUserInput("phone number");
                    try {
                        System.out.printf("Total: $%.2f\n", Records.viewPayments(number));
                    } catch (RecordNotFound e) {
                        System.out.println(e.getMessage());
                    }
                } break;

                case "view":
                    System.out.println("Command not found");
                    System.out.println("Did you mean \"view records\" or \"view payments\"?");
                    break;

                case "add": {
                    String name = getUserInput("name");
                    String number = getUserInput("phone number");
                    float minutes = Float.parseFloat(
                            getUserInput("number of minutes used"));
                    try {
                        Records.addRecord(name, number, minutes);
                        System.out.println("Record has been added");
                    } catch (RecordAlreadyExists e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                } break;

                case "modify": {
                    String number = getUserInput("phone number");
                    float minutes = Float.parseFloat(
                            getUserInput("number of minutes used"));
                    try {
                        Records.modifyRecord(number, minutes);
                        System.out.println("Record has been modified");
                    } catch (RecordNotFound e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                } break;

                case "delete": {
                    String number = getUserInput("phone number");
                    try {
                        Records.deleteRecord(number);
                        System.out.println("Record has been deleted");
                    } catch (RecordNotFound e) {
                        System.out.println(e.getMessage());
                    }
                } break;

                case "exit":
                    System.out.println("Goodbye, officer!");
                    return;

                case "help":
                    displayMenu();
                    break;

                default:
                    System.out.println("Command not found");
                    System.out.println("Type \"help\" for a list of available commands");
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
        System.out.println();
        System.out.printf("TELEx CLI v%s\n", Main.version);
        System.out.printf("Logged in as: %s\n", Main.user);
        System.out.println("Available commands:");
        for (String op : Ops) {
            System.out.println(op);
        }
    }

    private static String getUserInput(String name) {
        System.out.printf("Provide the %s: ", name);
        return scanner.nextLine();
    }

    private static void printCustomer(Customer customer) {
        System.out.println();
        System.out.printf(
            """
                Name: %s
                Number: %s
                Usage: %.1f
                Total: $%.2f
            """,
            customer.name, customer.phoneNumber,
            customer.usage, customer.total);
    }
}
