import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

                ArrayList<Payable> paymentQueue = new ArrayList<>();

                // payment methods
                CreditCard cc = new CreditCard("Hillary", 100.0, 200.0);
                MealPlan meal = new MealPlan("Hillary", 75.0);

                paymentQueue.add(cc);
                paymentQueue.add(meal);

                // payment queues
                for (Payable p : paymentQueue) {
                    p.processPayment(50.0);
                }

                // Print total transactions
                System.out.println("Total Transactions: " + PaymentMethod.totalTransactions);
            }
        }



