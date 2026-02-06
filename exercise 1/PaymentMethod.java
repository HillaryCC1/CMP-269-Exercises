abstract class PaymentMethod implements Payable {
    protected String accountHolder;
    protected double balance;

    // Static member to track transactions across the whole system
    public static int totalTransactions = 0;

    // Track last status message for getPaymentStatus()
    protected String paymentStatus = "No transactions yet.";

    public PaymentMethod(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Abstract method each subclass must implement
    public abstract void validateAccount();

    // Provide a shared implementation for the interface method
    @Override
    public String getPaymentStatus() {
        return paymentStatus;
    }
}


