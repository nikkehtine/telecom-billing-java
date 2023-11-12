public class Customer {
    public String name, phoneNumber;
    public float usage, total;

    public Customer(String name, String phoneNumber, float usage) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.setUsage(usage);
    }

    public void setUsage(float usage) {
        this.usage = usage;
        this.total = calculateBill();
    }

    private float calculateBill() {
        return this.usage * 0.05f;
    }
}
