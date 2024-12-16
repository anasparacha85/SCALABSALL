package ANASSCADALLLABS;
class BankAccount1 {
    private double balance;

    public BankAccount1(double initialBalance) {
        this.balance = initialBalance;
    }

    // Synchronized method to deposit money
    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " | New Balance: " + balance);
        notifyAll();
    }

    // Synchronized method to withdraw money
    public synchronized void withdraw(double amount) {
        System.out.println("Withdrawal request: " + amount);
        while (balance < amount) {
            System.out.println("Insufficient balance. Waiting for deposit...");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Withdrawal thread interrupted.");
            }
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount + " | Remaining Balance: " + balance);
    }

    // Method to check balance
    public synchronized double getBalance() {
        return balance;
    }
}
public class OPENeNDEDLAB {
	public static void main(String [] args) {
	
		 BankAccount1 account = new BankAccount1(100.0);

	        // Thread to handle withdrawals
	        Thread withdrawalThread = new Thread(() -> {
	            account.withdraw(200.0); // Trying to withdraw 200
	        });

	        // Thread to handle deposits
	        Thread depositThread = new Thread(() -> {
	            try {
	                Thread.sleep(2000); // Simulating delay for deposit
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	            account.deposit(150.0); // Depositing 150
	        });

	        withdrawalThread.start();
	        depositThread.start();

	        // Optional: Adding a thread to check balance
	        Thread balanceThread = new Thread(() -> {
	            try {
	                Thread.sleep(1000); // Simulate delay before checking balance
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	            System.out.println("Current Balance: " + account.getBalance());
	        });

	        balanceThread.start();
	    }
	}
