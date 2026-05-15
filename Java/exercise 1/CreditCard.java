class CreditCard extends PaymentMethod {
    private double creditLimit;

    public CreditCard(String accountHolder, double balance, double creditLimit) {
        super(accountHolder, balance); // must be first line
        this.creditLimit = creditLimit;
        validateAccount();
    }

    @Override
    public void validateAccount() {
        // Example validation: credit limit can't be negative
        if (creditLimit < 0) {
            creditLimit = 0;
        }
    }

    @Override
    public void processPayment(double amount) {
        if (amount <= 0) {
            paymentStatus = "Invalid amount. Transaction Declined.";
            System.out.println(paymentStatus);
            return;
        }

        // If amount exceeds available funds (balance + creditLimit), decline
        if (amount > (balance + creditLimit)) {
            paymentStatus = "Transaction Declined.";
            System.out.println(paymentStatus);
        } else {
            balance -= amount; // may go negative, representing credit usage
            PaymentMethod.totalTransactions++;
            paymentStatus = "Approved. Remaining (balance may be negative): " + balance;
            System.out.println(paymentStatus);
        }
    }
}