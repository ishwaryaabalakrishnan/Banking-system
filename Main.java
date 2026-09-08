import java.util.Scanner;

abstract class Account {
    long Accnum;
    String name;
    String branch_name;
    long phnum;
    String IFSC;
    double bal;

    Account(long Accnum, String name, String branch_name,
            long phnum, String IFSC, double bal) {
        this.Accnum = Accnum;
        this.name = name;
        this.branch_name = branch_name;
        this.phnum = phnum;
        this.IFSC = IFSC;
        this.bal = bal;
    }

    abstract void deposit(double amt);

    abstract void withdraw(double amt);

    abstract void transfer(String IFSC, String branch_name);
}

class Bank extends Account {

    Bank(long Accnum, String name, String branch_name,
         long phnum, String IFSC, double bal) {
        super(Accnum, name, branch_name, phnum, IFSC, bal);
    }

    @Override
    public void deposit(double amt) {
        if (amt <= 0) {
            System.out.println("Invalid deposit amount. Amount must be greater than 0.");
            return;
        }

        bal += amt;

        System.out.printf("Deposited: %.2f%n", amt);
        System.out.printf("Updated Balance: %.2f%n", bal);
    }

    @Override
    public void withdraw(double amt) {
        if (amt <= 0) {
            System.out.println("Invalid withdrawal amount. Amount must be greater than 0.");
            return;
        }

        if (amt > bal) {
            System.out.println("Insufficient balance.");
            return;
        }

        bal -= amt;

        System.out.printf("Withdrawn: %.2f%n", amt);
        System.out.printf("Remaining Balance: %.2f%n", bal);
    }

    @Override
    public void transfer(String IFSC, String branch_name) {
        if (IFSC == null || IFSC.trim().isEmpty()) {
            System.out.println("Invalid IFSC code.");
            return;
        }

        if (branch_name == null || branch_name.trim().isEmpty()) {
            System.out.println("Invalid branch name.");
            return;
        }

        this.IFSC = IFSC;
        this.branch_name = branch_name;

        System.out.println("Transfer details updated successfully.");
        System.out.println("Branch: " + branch_name);
        System.out.println("IFSC: " + IFSC);
    }

    public void showDetails() {
        System.out.println("\n----- Account Details -----");
        System.out.println("Account Number: " + Accnum);
        System.out.println("Name: " + name);
        System.out.println("Branch: " + branch_name);
        System.out.println("Phone: " + phnum);
        System.out.println("IFSC: " + IFSC);
        System.out.printf("Balance: %.2f%n", bal);
        System.out.println("---------------------------");
    }
}

public class Main {

    private static void displayMenu() {
        System.out.println("\n===== Banking System =====");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Show Account Details");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static double readPositiveAmount(
            Scanner sc,
            String message) {

        while (true) {
            System.out.print(message);

            if (!sc.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
                continue;
            }

            double amount = sc.nextDouble();

            if (amount <= 0) {
                System.out.println(
                        "Amount must be greater than 0.");
                continue;
            }

            return amount;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Bank b1 = new Bank(
                33442,
                "Ishu",
                "Chennai",
                56787,
                "ISDRT",
                5000.67
        );

        boolean running = true;

        System.out.println("Welcome to the Banking System!");

        while (running) {

            displayMenu();

            if (!sc.hasNextInt()) {
                System.out.println(
                        "Invalid choice. Please enter a number from 1 to 5."
                );
                sc.next();
                continue;
            }

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    double depositAmount = readPositiveAmount(
                            sc,
                            "Enter deposit amount: "
                    );

                    b1.deposit(depositAmount);
                    break;

                case 2:
                    double withdrawAmount = readPositiveAmount(
                            sc,
                            "Enter withdrawal amount: "
                    );

                    b1.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.print("Enter IFSC: ");
                    String IFSC = sc.next();

                    System.out.print("Enter branch name: ");
                    String branchName = sc.next();

                    b1.transfer(IFSC, branchName);
                    break;

                case 4:
                    b1.showDetails();
                    break;

                case 5:
                    System.out.println("Thank you for using the Banking System.");
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please select an option from 1 to 5."
                    );
                    break;
            }
        }

        sc.close();
    }
}
