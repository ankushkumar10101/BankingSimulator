import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Welcome to Java Bank ---");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Set 4-digit PIN: ");
                    String pin = sc.nextLine();
                    String accNo = bank.createAccount(name, pin);
                    System.out.println("Account created. Your Account No: " + accNo);
                    break;

                case 2:
                    System.out.print("Enter Account No: ");
                    String acc = sc.nextLine();
                    System.out.print("Enter PIN: ");
                    String loginPin = sc.nextLine();
                    Account user = bank.authenticate(acc, loginPin);
                    if (user != null) {
                        loggedInMenu(bank, user, sc);
                    } else {
                        System.out.println("Invalid login!");
                    }
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    return;
            }
        }
    }

    private static void loggedInMenu(Bank bank, Account user, Scanner sc) {
        while (true) {
            System.out.println("\n--- Account Menu (" + user.getName() + ") ---");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Transactions");
            System.out.println("5. Logout");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: $" + user.getBalance());
                    break;
                case 2:
                    System.out.print("Amount to deposit: ");
                    double deposit = sc.nextDouble();
                    bank.deposit(user, deposit);
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Amount to withdraw: ");
                    double withdraw = sc.nextDouble();
                    if (bank.withdraw(user, withdraw)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case 4:
                    bank.showTransactions(user.getAccountNumber());
                    break;
                case 5:
                    return;
            }
        }
    }
}
