import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Long, Account> accounts = new HashMap<>();
        HashMap<Long, Transaction> transactions = new HashMap<>();
        long transactionCounter = 1;
        boolean exit = false;
        while (!exit) {
            System.out.println("\nBank Application Menu:");
            System.out.println("1. Create New Account");
            System.out.println("2. Display Account Details");
            System.out.println("3. Check Balance");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Transfer");
            System.out.println("7. Update Account");
            System.out.println("8. Set Password");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    long accNo = sc.nextLong();
                    if (accounts.containsKey(accNo)) {
                        System.out.println("Account with account number " + accNo + " already exists.");
                    } else {
                        System.out.print("Enter username: ");
                        String username = sc.next();
                        System.out.print("Enter account type (e.g., Savings, Checking): ");
                        String accType = sc.next();
                        System.out.print("Enter initial balance: ");
                        double balance = sc.nextDouble();
                        System.out.print("Enter password: ");
                        String password = sc.next();
                        System.out.print("Enter phone number: ");
                        long phoneNo = sc.nextLong();
                        System.out.print("Enter address: ");
                     sc.nextLine(); // consume newline
                        String address = sc.nextLine();
                        System.out.print("Enter branch: ");
                        String branch = sc.next();
                        Account newAccount = new Account(username, accNo, accType, balance, password, phoneNo, address, branch);
                        accounts.put(accNo, newAccount);
                        System.out.println("Account created successfully!");
                    }
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    accNo = sc.nextLong();
                    Account account = accounts.get(accNo);
                    if (account != null) {
                        account.displayAccountDetails();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    accNo = sc.nextLong();
                    account = accounts.get(accNo);
                    if (account != null) {
                        System.out.println("Balance: $" + account.checkBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                
                case 4:
                    System.out.print("Enter account number: ");
                    accNo = sc.nextLong();
                    account = accounts.get(accNo);
                    if (account != null) {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = sc.nextDouble();
                        Transaction depositTransaction = new Transaction(account.getAcc_no(), account.getAcc_no(), "Deposit", transactionCounter++, new Date(), depositAmount);
                        depositTransaction.deposit(account, depositAmount);
                        transactions.put(depositTransaction.getTransactionId(), depositTransaction);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter account number: ");
                    accNo = sc.nextLong();
                    account = accounts.get(accNo);
                    if (account != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = sc.nextDouble();
                        Transaction withdrawTransaction = new Transaction(account.getAcc_no(), account.getAcc_no(), "Withdraw", transactionCounter++, new Date(), withdrawAmount);
                        withdrawTransaction.withdraw(account, withdrawAmount);
                        transactions.put(withdrawTransaction.getTransactionId(), withdrawTransaction);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter source account number: ");
                    long fromAccNo = sc.nextLong();
                    System.out.print("Enter destination account number: ");
                    long toAccNo = sc.nextLong();
                    account = accounts.get(fromAccNo);
                    Account toAccount = accounts.get(toAccNo);
                    if (account != null && toAccount != null) {
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = sc.nextDouble();
                        Transaction transferTransaction = new Transaction(account.getAcc_no(), toAccount.getAcc_no(), "Transfer", transactionCounter++, new Date(), transferAmount);
                        transferTransaction.transfer(account, toAccount, transferAmount);
                        transactions.put(transferTransaction.getTransactionId(), transferTransaction);
                    } else {
                        System.out.println("One or both accounts not found.");
                    }
                    break;
                case 7:
                    System.out.print("Enter account number to update: ");
                    accNo = sc.nextLong();
                    account = accounts.get(accNo);
                    if (account != null) {
                        System.out.print("Enter new username: ");
                        String newUsername = sc.next();
                        System.out.print("Enter new phone number: ");
                        long newPhoneNo = sc.nextLong();
                        System.out.print("Enter new address: ");
                     sc.nextLine(); // consume newline
                        String newAddress = sc.nextLine();
                        System.out.print("Enter new branch: ");
                        String newBranch = sc.next();
                        account.update(newUsername, newPhoneNo, newAddress, newBranch);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 8:
                    System.out.print("Enter account number to set password: ");
                    accNo = sc.nextLong();
                    account = accounts.get(accNo);
                    if (account != null) {
                        System.out.print("Enter new password: ");
                        String newPassword = sc.next();
                        account.setPassword(newPassword);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 9:
                    exit = true;
                    System.out.println("Exiting the application...");
                    break;
                
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
     sc.close();
    }
}