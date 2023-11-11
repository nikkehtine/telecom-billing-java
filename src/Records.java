import TelecomExceptions.RecordNotFound;

import java.util.ArrayList;

public class Records {
    public static ArrayList<Customer> List = new ArrayList<>();

    private static Customer findRecord(String phoneNumber) throws RecordNotFound {
        for (Customer customer : List) {
            if (customer.phoneNumber.equals(phoneNumber)) {
                return customer;
            }
        }
        throw new RecordNotFound();
    }

    /* Get stuff */
    public static ArrayList<Customer> viewRecords() {
        return List;
    }
    public static Customer searchRecord(String phoneNumber) {
        return findRecord(phoneNumber);
    }
    public static float viewPayments(String phoneNumber) {
        Customer customer = findRecord(phoneNumber);
        return customer.total;
    }
    /* Modify stuff */
    public static void addRecord(String name, String phoneNumber, float minutes) {
        try {
            findRecord(phoneNumber);
        } catch (RecordNotFound notFound) {
            List.add(new Customer(name, phoneNumber, minutes));
        }
    }
    public static void modifyRecord(String phoneNumber, float usage) {
        Customer customer = findRecord(phoneNumber);
        customer.usage = usage;
    }
    public static void deleteRecord(String phoneNumber) {
        Customer customer = findRecord(phoneNumber);
        List.remove(customer);
    }
}
