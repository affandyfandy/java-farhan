package Q4;

public interface Account {
    boolean withdraw(int accountId, double amount);

    boolean deposit(int accountId, double amount);

    double queryBalance(int accountId);

    boolean login(String username, String password);

    boolean logout(String username);

    static void bankPolicy() {
        System.out.println("Bank Policy: Withdrawal limit is $1000 per day.");
    }
}
