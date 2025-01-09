import java.util.HashMap;
public class AdminControl {
    private int id;
    private String name;
    private String pass;
    
    private static HashMap<String, String> adminCredentials = new HashMap<>();
    
    public AdminControl(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public void addAccount(HashMap<Long, Account> accounts, Account account) {
        if (accounts.containsKey(account.getAcc_no())) {
            System.out.println("Account with account number " + account.getAcc_no() + " already exists.");
        } else {
            accounts.put(account.getAcc_no(), account);
            System.out.println("Account added successfully!");
        }
    }
   
    public void closeAccount(HashMap<Long, Account> accounts, long accNo) {
        if (accounts.containsKey(accNo)) {
            accounts.remove(accNo);
            System.out.println("Account number " + accNo + " closed successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    
    public void viewAllAccounts(HashMap<Long, Account> accounts) {
        if (accounts.isEmpty()) {
            System.out.println("No accounts to display.");
        } else {
            System.out.println("All Accounts:");
            for (Account account : accounts.values()) {
                account.displayAccountDetails();
                System.out.println("-----------");
            }
        }
    }

    
    public void searchAccount(HashMap<Long, Account> accounts, long accNo) {
        Account account = accounts.get(accNo);
        if (account != null) {
            System.out.println("Account Details:");
            account.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    
    public void changePassword(String oldPass, String newPass) {
        if (this.pass.equals(oldPass)) {
            this.pass = newPass;
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Incorrect old password. Password not changed.");
        }
    }

    
    public void displayAdminDetails() {
        System.out.println("Admin ID: " + id);
        System.out.println("Admin Name: " + name);
    }
}