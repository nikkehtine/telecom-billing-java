import TelecomExceptions.RecordAlreadyExists;
import TelecomExceptions.RecordNotFound;

import java.util.ArrayList;

public class Records {
    public static ArrayList<Customer> List = new ArrayList<>();

    /* Get stuff */
    public static ArrayList<Customer> viewRecords() {
        return List;
    }

    public static Customer searchRecord(String phoneNumber) {
        for (Customer customer : List) {
            if (customer.phoneNumber.equals(phoneNumber))
                return customer;
        }
        throw new RecordNotFound(phoneNumber);
    }

    public static float viewPayments(String phoneNumber) {
        Customer customer = searchRecord(phoneNumber);
        return customer.total;
    }

    /* Modify stuff */
    public static void addRecord(String name, String phoneNumber, float minutes) {
        try {
            searchRecord(phoneNumber);
        } catch (RecordNotFound notFound) {
            List.add(new Customer(name, phoneNumber, minutes));
            return;
        }
        throw new RecordAlreadyExists(phoneNumber);
    }

    public static void modifyRecord(String phoneNumber, float usage) {
        Customer customer = searchRecord(phoneNumber);
        customer.setUsage(usage);
    }

    public static void deleteRecord(String phoneNumber) {
        Customer customer = searchRecord(phoneNumber);
        List.remove(customer);
    }
}
