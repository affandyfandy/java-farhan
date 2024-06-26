package Q4;

public class App {
    public static void main(String[] args) {
        SavingAccount savingAccount = new SavingAccount(1, 1000.0);
        CurrentAccount currentAccount = new CurrentAccount(2, 500.0, 200.0);

        // Test SavingAccount
        savingAccount.deposit(1, 200.0);
        System.out.println("Saving Account Balance: " + savingAccount.queryBalance(1));
        savingAccount.addInterest(0.05);
        System.out.println("Saving Account Balance after interest: " + savingAccount.queryBalance(1));

        // Test CurrentAccount
        currentAccount.withdraw(2, 600.0);
        System.out.println("Current Account Balance: " + currentAccount.queryBalance(2));
        currentAccount.setOverdraftLimit(300.0);
        System.out.println("Current Account Balance after setting overdraft limit: " + currentAccount.queryBalance(2));

        // Use static method in interface
        Account.bankPolicy();
    }
}

