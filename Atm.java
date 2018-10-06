import java.util.ArrayList;

/**
 * This is the actual Automated Teller Machine (atm) Here is where we do the actual work The Atm maintains a supply
 * of money and its currentUser It uses a ConsoleAtmUserInterface object to handle all the interactions with the user
 @version 1.0
 @author Ivan Sepulveda
 */
public class Atm {

    private static int sessionNumber = 1;
    private ConsoleAtmUserInterface	ui = new ConsoleAtmUserInterface();
    // Shouldn't there be a condition so that a user cannot withdraw an amount that would make this moneySupply negative
    /**
     * mony inside the atm
     */
    private int moneySupply = 0;
    /**
     * person currently using the atm
     */
    private BankUser currentUser = null;

    /**
     * Atm construction, initializes machine and it's moneySupply
     * @param moneySupply intial money inside atm
     */
    public Atm(int moneySupply) {
        this.moneySupply = moneySupply;
    }
    /**
     * Reads the user Atm card gets the id and the pin and then if there is a matching user sets the current Atm
     * user to the matching user on success otherwise the Atm reports an error and continues to ask the user for id
     * and pin
     * Note: IF there is already a current user when this is called, then handle as a continuation of the currentSession
     * @return true if session starts; false if user wants to exit
     */
    public boolean startSession(){
        String idInput = ui.readCard();
        // if the user wants to exit on the id input, no need to ask for their pin
        if (idInput == null){
            return false;
        }
        String pinInput = ui.readPin();
        // loop to keep trying until user quits or enters correct account information
        // in the future and in real life, you'd probably want to add a count that could break loop
        // so that if someone tried 20 different accounts, they're obviously trying to pull fraud
        while (BankUser.getUser(idInput, pinInput) == null) {
            ui.displayError("Invalid ID or PIN. Please try again or enter Q to exit.");
            idInput = ui.readCard();
            if (idInput.equals(ui.getQuitString())){
                return false;
            }
            pinInput = ui.readPin();
        }
        System.out.println("Atm Session#" + sessionNumber + "[" + moneySupply + " in ATM]");
        sessionNumber += 1;
        currentUser = BankUser.getUser(idInput, pinInput);
        ui.displayMessage("Welcome " + currentUser.getName() + "!");
        return true;
    }
    /**
     * Explicitly shuts off the Atm Setting currentUser to null
     */
    public void endSession() {
        ui.returnCard();
        currentUser = null;
    }
    /**
     * Requests the user to perform one of the following transactions Check Balance Withdraw Money Deposit OR Exit and
     * then performs the transaction
     * @return the TransactionType performed or null on Exit
     */
    public TransactionType performTransaction(){

        TransactionType transactionChoice = ui.chooseTransactionType();
        if (transactionChoice != null){
            System.out.println("You chose:" + transactionChoice);
        }
        if (transactionChoice == TransactionType.DEPOSIT){
            doDeposit();
            return transactionChoice;
        }
        if (transactionChoice == TransactionType.WITHDRAWAL){
            doWithdrawal();
            return transactionChoice;
        }
        if (transactionChoice == TransactionType.CHECK_BALANCE){
            doCheckBalance();
            return transactionChoice;
        }
        return null;
    }

    /**
     * Performs a deposit of money into an Account 1. Asks user in which Account the deposit is to be made
     * 2. Get the Deposit Envelope
     * 3. Make the Deposit
     */
    private void doDeposit() {
        Account accountSelected = ui.readAccount(currentUser.getAccounts());
        if (accountSelected==null){
            return;
        }
        ui.displayMessage("You selected: " + accountSelected.toString());
        int amountToDeposit = ui.takeDepositEnvelope();
        accountSelected.credit(amountToDeposit);
        this.moneySupply += amountToDeposit;
    }


    /**
     * Performs a withdrawal of money from an Account
     * 1. Asks user from which Account withdrawal is to be made
     * 2. Ask user amount to withdraw
     * 3. If account does not have enough money for withdrawal, report (via ui) error;
     * otherwise perform withdrawal and give user money
     */
    private void doWithdrawal() {
        Account fromThisAccount = ui.readAccount(currentUser.getAccounts());
        if (fromThisAccount==null){
            return;
        }
        System.out.println("Account balance before withdrawal:" + fromThisAccount.getBalance());
        System.out.println("You selected: " + fromThisAccount.toString());
        int withdrawalRequest = ui.readWithdrawalAmount();
        if (withdrawalRequest > this.moneySupply){
            System.out.println("You cannot withdraw that much. Not enough reserves inside ATM");
            System.out.println("Max withdraw at the moment is "+ this.moneySupply);
            return;
        }
        if (fromThisAccount.debit(withdrawalRequest)){
            this.moneySupply -= withdrawalRequest;
            ui.deliverMoney(withdrawalRequest);
            return;
        }
        ui.displayError("Insufficient funds!");
    }
    /**
     * Reports to user how much money is in an Account
     * 1. Asks user which Account to check Balance
     * 2. Display the Balance
     */

    private void doCheckBalance() {
        Account fromThisAccount = ui.readAccount(currentUser.getAccounts());
        if (fromThisAccount != null){
            ui.displayMessage("Balance is: $" + fromThisAccount.getBalance());

        }

    }

}
