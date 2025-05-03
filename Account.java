public class Account {
    private String accountNumber;
    private String name;
    private String pin;
    private double balance;

    public Account(String accountNumber, String name, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getName() { return name; }
    public String getPin() { return pin; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public String toCSV() {
        return accountNumber + "," + name + "," + pin + "," + balance;
    }

    public static Account fromCSV(String line) {
        String[] parts = line.split(",");
        return new Account(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
    }
}
