interface ATM {
    boolean withdraw(double amount);
    boolean deposit(double amount);
    double queryBalance();
    boolean login(String id, String accountID);
    boolean logout();

    static void displayBankName() {
        System.out.println("Welcome to Our Bank");
    }
}

abstract class Account implements ATM {
    protected String idATM;
    protected String accountID;
    protected double balance;

    public Account(String idATM, String accountID) {
        this.idATM = idATM;
        this.accountID = accountID;
        this.balance = 0.0;
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
            return true;
        } else {
            System.out.println("Insufficient balance. Withdrawal failed.");
            return false;
        }
    }

    @Override
    public boolean deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. Current balance: " + balance);
        return true;
    }

    @Override
    public double queryBalance() {
        return balance;
    }

    @Override
    public boolean login(String id, String accountID) {
        if (this.idATM.equals(id) && this.accountID.equals(accountID)) {
            System.out.println("Login Success");
            return true;
        } else {
            System.out.println("No Account Detected");
            return false;
        }
    }

    @Override
    public boolean logout() {
        System.out.println("Logged out successfully.");
        return true;
    }
}

class SavingAccount extends Account {
    public SavingAccount(String idATM, String accountID) {
        super(idATM, accountID);
    }

    // Additional unique methods for SavingAccount
}

class CurrentAccount extends Account {
    public CurrentAccount(String idATM, String accountID) {
        super(idATM, accountID);
    }

    // Additional unique methods for CurrentAccount
}

public class Q4 {
    public static void main(String[] args) {
        SavingAccount savingsAccount = new SavingAccount("00001", "550055");
        CurrentAccount currentAccount = new CurrentAccount("00002", "005500");

        ATM.displayBankName();

        savingsAccount.deposit(500.0);
        System.out.println("Savings Account Balance: " + savingsAccount.queryBalance());
        savingsAccount.withdraw(200.0);
        System.out.println("Savings Account Balance: " + savingsAccount.queryBalance());
        savingsAccount.logout();

        System.out.println();

        ATM.displayBankName();

        currentAccount.deposit(100.0);
        System.out.println("Current Account Balance: " + currentAccount.queryBalance());
        currentAccount.withdraw(300.0);
        System.out.println("Current Account Balance: " + currentAccount.queryBalance());
        currentAccount.logout();
    }
}
