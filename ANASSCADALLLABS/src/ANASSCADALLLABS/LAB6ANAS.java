package ANASSCADALLLABS;
class bankAccount{
	int balance;
	public bankAccount(int balance) {
		this.balance=balance;
	}
	synchronized public void  withdraw(int amount) {
		balance=balance-amount;
		System.out.println(balance);
		try {
			Thread.sleep(400);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
class Accountholder1 extends Thread{
	bankAccount b;
	public Accountholder1(bankAccount b) {
		this.b=b;
	}
	public void run() {
		b.withdraw(45000);
	}
	
}
class Accountholder2 extends Thread{
	bankAccount b;
	public Accountholder2(bankAccount b) {
		this.b=b;
	}
	public void run() {
		b.withdraw(20000);
	}
	
}
public class LAB6ANAS {
	public static void main(String []args) {
		bankAccount ba=new bankAccount(50000);
		Accountholder1 ac1=new Accountholder1(ba);
		Accountholder2 ac2=new Accountholder2(ba);
		ac1.start();
		ac2.start();
		
	}

}
