import java.util.ArrayList;
/**
 * Defines a user of the Atm, tracking the bankUsers id and pin and the accounts the user owns
 @version 1.0
 @author Ivan Sepulveda
 */
public class BankUser{
	/**
	 * ArrayList of bank users
	 */
	private static ArrayList<BankUser> bankUsers = new ArrayList<>();
	/**
	 * user's name
	 */
	private String name;
	/**
	 * User's identifier
	 */
	private String id;
	/**
	 * user's pin
	 */
	private String pin;
	/**
	 * array of this user's accounts
	 */
	private Account[] accounts;


	/**
	 * Creates a BankUser and intializes the BankUser to have the properties entered in the parameters
	 * @param name BankUser's name
 	 * @param id BankUser's id
	 * @param pin BankUser's pin
	 * @param accounts BankUser's array of accounts
     * @throws java.lang.IllegalArgumentException - if id is already in use
	 */
    public BankUser(String name, String id, String pin, Account[] accounts){
    	if (bankUsers.size() > 0){
			for (BankUser checkUser: bankUsers){
				if (id.equals(checkUser.id)){
					throw new java.lang.IllegalArgumentException("Id already in use");
				}
			}
		}
    	this.name = name;
    	this.pin = pin;
    	this.id = id;
    	this.accounts = accounts;
    	bankUsers.add(this);
    }
	/**
	 * This is used for testing the BankUser class by itself Create two bankUsers - one has 3 accounts and one has
	 * two accounts and then calls for each user getAccounts, getName - printing out the results for each Finally,
	 * Test the static method getUser and then print out the BankUser you get
	 * @param args unused
	 */
	public static void main(String args[]) {
	}

	/**
	 * Gets the user matching specified id IF the pin is correct!
	 * @param id id of user to get
	 * @param pin user's pin
	 * @return specified user or null if not found
	 */
	public static BankUser getUser(String id, String pin){
		for (BankUser checkThisUser : bankUsers) {
			if (checkThisUser.pin.equals(pin) && checkThisUser.id.equals(id)){
				return checkThisUser;
			}
		}
		return null;
	}
	/**
	 * @return the name of the BankUser
	 */
	String getName(){
		return this.name;
	}
	/**
	* @return the array of BankUser's accounts
	*/
	public Account[] getAccounts(){
		return this.accounts;
	}
	/**
	* @return all bank user information in form of a string
	*/
	@Override
	public String toString(){
		return this.name + " [id=" + this.id + ", pin=" + this.pin +"]";
	}

}
