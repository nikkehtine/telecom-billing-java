public class Customer {
    private String name, phone_number;
    private double usage, total_bill;

    public Customer(String name, String phone_number, double usage) {
        this.name = name;
        this.phone_number = phone_number;
        this.usage = usage;
        this.total_bill = calculateBill();
    }

    private double calculateBill() {
        return 69.9;
    }
}
