import java.io.*;
import java.util.*;

public class Bank {
    private Map<String, Account> accounts = new HashMap<>();
    private final String ACCOUNTS_FILE = "accounts.csv";

    public Bank() {
        loadAccounts();
    }

    private void loadAccounts() {
        try {
            File file = new File(ACCOUNTS_FILE);
            if (!file.exists()) return;

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Account acc = Account.fromCSV(line);
                accounts.put(acc.getAccountNumber(), acc);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error loading accounts.");
        }
    }

    private void saveAccounts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ACCOUNTS_FILE))) {
            for (Account acc : accounts.values()) {
                writer.println(acc.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts.");
        }
    }

    public String createAccount(String name, String pin) {
        String accNo = String.valueOf(1000 + accounts.size() + 1);
        Account newAcc = new Account(accNo, name, pin, 0.0);
        accounts.put(accNo, newAcc);
        saveAccounts();
        logTransaction(accNo, "Account created");
        return accNo;
    }

    public Account authenticate(String accNo, String pin) {
        Account acc = accounts.get(accNo);
        return (acc != null && acc.getPin().equals(pin)) ? acc : null;
    }

    public void deposit(Account acc, double amount) {
        acc.setBalance(acc.getBalance() + amount);
        saveAccounts();
        logTransaction(acc.getAccountNumber(), "Deposit: +" + amount);
    }

    public boolean withdraw(Account acc, double amount) {
        if (acc.getBalance() >= amount) {
            acc.setBalance(acc.getBalance() - amount);
            saveAccounts();
            logTransaction(acc.getAccountNumber(), "Withdrawal: -" + amount);
            return true;
        }
        return false;
    }

    public void showTransactions(String accNo) {
        String fileName = "txn_" + accNo + ".txt";
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("No transaction history.");
                return;
            }

            Scanner scanner = new Scanner(file);
            System.out.println("Transaction History:");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading transactions.");
        }
    }

    private void logTransaction(String accNo, String detail) {
        String fileName = "txn_" + accNo + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(new Date() + " - " + detail);
        } catch (IOException e) {
            System.out.println("Error writing transaction.");
        }
    }
}
