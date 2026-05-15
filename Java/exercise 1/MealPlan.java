class MealPlan extends PaymentMethod {

    public MealPlan(String accountHolder, double balance) {
        super(accountHolder, balance);
        validateAccount();
    }

    @Override
    public void validateAccount() {
        // Meal plan balances cannot be negative
        if (balance < 0) {
            balance = 0;
        }
    }

    @Override
    public void processPayment(double amount) {
        if (amount <= 0) {
            paymentStatus = "Invalid amount. Transaction Declined.";
            System.out.println(paymentStatus);
            return;
        }

        // Only allow if the balance is sufficient
        if (amount > balance) {
            paymentStatus = "Meal Plan Declined: Insufficient balance.";
            System.out.println(paymentStatus);
        } else {
            balance -= amount;
            PaymentMethod.totalTransactions++;
            paymentStatus = "Meal Plan Approved. Remaining balance: " + balance;
            System.out.println(paymentStatus);
        }
    }
}

