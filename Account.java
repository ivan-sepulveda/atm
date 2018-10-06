
/**
 * Keep track of everything going on with an account type of account; current balance, and account ID
 * Note: the accountId is set by the Account Class when the account is created!!
 @version 1.0
 @author Ivan Sepulveda
 */
class Account{
	/**
	 * Number of ids issued used to generate next id number
	 */
	private static int idCount = 0;
    /**
     * type is type of account, checking, saving, certificate of deposit, etc.
     */
    private String type;
    /**
     * name of the account i.e "Ivan's Ferrari fund"
     */
    private String name;
    /**
     * current amount in account
     */
    private int balance;
    /**
	 * unique account identifier
	 */
	private String accountId;


	/**
	 *
	 * @param type String type of account, e.g., "Checking" or "Savings"
	 * @param name how the user references the account
	 * @param amount initial dollars in account
	 */
	Account(String type, String name, int amount){
		idCount ++;
    	this.type = type;
    	this.name = name;
    	this.balance = amount;
    	this.accountId = String.valueOf(idCount);
	}
//	 Note to Prof. Halperin or Prateek: This is the Lab 4 description you had previously
//	 Previously you had us initialize everything in this main. But the AtmMain Class Javadoc has us initialize there
//	 For simplicity, I am going to initialize different accounts in both classes for now
	/**
	 * This is used for testing the Account class by itself Create two accounts and then call for each Account
	 * each of the methods credit, debit, getBalance - printing out the Account and the results from each
	 * @param args unused
	 */
	static void main(String args[]) {
		Account accountA = new Account("Checking", "Spending Money", 5000);
		accountA.debit(200);
		accountA.credit(2000);
		System.out.println(accountA.getBalance());
		Account accountB = new Account("Checking", "Spending Money", 5000);
		accountB.debit(100);
		accountB.credit(1000);
		System.out.println(accountB.getBalance());
	}
	/**
	 * adds to account balance
	 * @param amount deposit dollars
	 */
	void credit(int amount) {
		this.balance += amount;
	}
	/**
	 * subtracts from account balance
	 * @param amount withdrawal dollars
	 * @return true if enough money in account for withdrawal; else false
	 */
	boolean debit(int amount){
		if (amount > this.balance){
			return false;
		}
		this.balance -= amount;
		return true;
	}
	/**
	 * gets current account balance
	 * @return current account balance
	 */
	int getBalance() {
		return balance;
	}

	/**
	 * includes name, type, and id in the string representation
	 */
	@Override
	public String toString(){
		return this.name + " [" + this.type + ": id=" + this.accountId +"]";
	}
}