package Q4;

public class SavingAccount implements Account {
    private int accountId;
    private double balance;

    public SavingAccount(int accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int accountId, double amount) {
        if (this.accountId == accountId && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean deposit(int accountId, double amount) {
        if (this.accountId == accountId) {
            balance += amount;
            return true;
        }
        return false;
    }

    @Override
    public double queryBalance(int accountId) {
        if (this.accountId == accountId) {
            return balance;
        }
        return 0.0;
    }

    @Override
    public boolean login(String username, String password) {
        // Dummy login implementation
        return "user".equals(username) && "pass".equals(password);
    }

    @Override
    public boolean logout(String username) {
        // Dummy logout implementation
        return "user".equals(username);
    }

    public void addInterest(double interestRate) {
        balance += balance * interestRate;
    }
}
