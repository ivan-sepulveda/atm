
/**
 * Standalone main routine to kick things off
 @version 1.0
 @author Ivan Sepulveda
 */
public class AtmMain {
    /**
     * initialize the world
     * <ul>
     *     <li>The atm itself</li>
     *     <li>At least 3 different users each with 1 to 3 of their own accounts.</li>
     * </ul>
     * Then loops servicing users until the operator quits
     * @param args unused
     */
    public static void main(java.lang.String[] args){
        System.out.println("Welcome to the Bank of USF ATM machine");
        // Test 1: user1 with 3 different accounts
        Account firstUserAccount1 = new Account("Checking", "Spending Money", 5000);
        Account firstUserAccount2 = new Account("Savings", "Ferrari Fund", 2000);
        Account firstUserAccount3 = new Account("Savings", "Rolex Fund", 10000);
        Account[] firstUserAccountsArray = new Account[]{firstUserAccount1, firstUserAccount2, firstUserAccount3};
        BankUser user1 = new BankUser("Ivan", "111", "123", firstUserAccountsArray);
        System.out.println("Created the user User " + user1);
        // Test 2: user2 with 2 different accounts
        Account secondUserAccount1 = new Account("Checking", "Spending Money", 25000);
        Account secondUserAccount2 = new Account("Savings", "McLaren Fund", 500000);
        Account[] secondUserAccountsArray = new Account[]{secondUserAccount1, secondUserAccount2};
        BankUser user2 = new BankUser("Prateek", "222", "456", secondUserAccountsArray);
        System.out.println("Created the user User " + user2);
        // Test 3: user3 with 1  accounts
        Account thirdUserAcount1 = new Account("Savings", "Retirement Fund", 1000000);
        Account[] thirddUserAccountsArray = new Account[]{thirdUserAcount1};
        BankUser user3 = new BankUser("Doug", "333", "789", secondUserAccountsArray);
        System.out.println("Created the user User " + user3);
        Atm thisMachine = new Atm(1000);
        ConsoleAtmUserInterface currentConsole = new ConsoleAtmUserInterface();
        String customerInput = "";
//        while (!customerInput.equals(currentConsole.getQuitString())) {
        TransactionType mostRecentTransaction;
        while (thisMachine.startSession()){
            mostRecentTransaction = thisMachine.performTransaction();
            System.out.println(mostRecentTransaction);
            while (mostRecentTransaction != null){
                mostRecentTransaction = thisMachine.performTransaction();
            }
        thisMachine.endSession();
        }
//            customerInput = "Q";

        currentConsole.displayMessage("Bye!");
    }
}
