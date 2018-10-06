import java.util.InputMismatchException;

/**
 * Simple Console input and output inDevice for an Atm uses System.out to print information
 * and System.in to get information and answers from the user.
 @version 1.0
 @author Ivan Sepulveda
 */
class ConsoleAtmUserInterface {
    private java.util.Scanner inDevice;
    /**
     * Magic String to enter to exit the Atm
     */
    private static java.lang.String QUIT_STRING = "Q";
    /**
     * Initializes the inDevice, readying it for use
     */
    public ConsoleAtmUserInterface() {
        this.inDevice = new java.util.Scanner(System.in);
    }
    /**
     *
     * @return the string read or null if there was an error
     */

    private String readString(){
        String inputString;
        inputString = inDevice.next();
        return inputString;
    }
    /**
     *
     * @return the integer read or 0 if there was an error
     */
    private int readNumber(){
        while(!inDevice.hasNextInt()) {
            inDevice.next();
            System.out.println("Please enter a valid integer");
        }
        int inputInt = inDevice.nextInt();
        return inputInt;
    }

    /**
     * Asks the user for their card
     * @return the card id or null if the user wants to quit
     */
    String readCard(){
        System.out.print("Please enter your card id (or Q to quit): ");
        String cardIdInput = readString();
        if (cardIdInput.equals(QUIT_STRING)){
            return null;
        }
        return cardIdInput;
    }
    String readPin(){
        System.out.print("Please enter your PIN: ");
        String pinInput = readString();
        if (pinInput.equals(QUIT_STRING)){
            return null;
        }
        return pinInput;
    }

    /**
     * Lists out available transaction types and allows the user to pick one using a number-format input
     * @return Transactiontype
     */
    TransactionType chooseTransactionType(){
        int transactionTypeSelection;
        System.out.println("Chose a transaction below:");

        int returnCardOption = TransactionType.values().length + 1;
        for (int j = 0; j < TransactionType.values().length; j++){
            System.out.println((j+1) + ": " + TransactionType.values()[j].toString());
        }
        System.out.println((returnCardOption) + ": Return Card");
        transactionTypeSelection = readNumber();
        if (transactionTypeSelection == returnCardOption) {
            return null;
        }
        while (transactionTypeSelection < 1 | transactionTypeSelection > returnCardOption){
            for (int j = 0; j < TransactionType.values().length; j++){
                System.out.println((j+1) + ": " + TransactionType.values()[j].toString());
            }
            System.out.println((returnCardOption) + ": Return Card");
            transactionTypeSelection = readNumber();
            if (transactionTypeSelection == returnCardOption) {
                return null;
            }
        }
        return TransactionType.values()[transactionTypeSelection-1];
    }

    /**
     * Give user back their Atm card (here we just say "Here's your card back!")
     */
    void returnCard(){
        System.out.println("Here's your card back!");
    }
    // for readAccount, should there be a loop to give user another chance in case it they hit 20 instead of 2
    /**
     * 1. List all the accounts the user has (including types!) 2. Asks user to select an account
     * @param accounts list of accounts to display
     * @return selected account or null if none selected
     */
    Account readAccount(Account[] accounts){
        System.out.println("Please pick one of the options below.");
        int cancelOption = accounts.length + 1;
        for (int k=0; k < accounts.length; k++){
            System.out.println(k+1 + ". " + accounts[k]);
        }
        System.out.println(cancelOption + ". CANCEL");
        int selection = readNumber();
        if (selection == cancelOption){
            return null;
        }
        while (selection < 1 || selection > cancelOption) {
            System.out.println("Please pick one of the options below.");
            for (int k=0; k < accounts.length; k++){
                System.out.println(k+1 + ". " + accounts[k]);
            }
            System.out.println(cancelOption + ". CANCEL");
            selection = readNumber();
        }
        if (selection == cancelOption){
            System.out.println("Canceling");
            return null;
        }
        return accounts[selection-1];
    }

    /**
     * Ask the user how much is in the envelope
     * @return amount user enters (must be more than zero)
     */
    int takeDepositEnvelope(){
        int envelopeAmountInput;
        do {
            System.out.print("Amount in the deposit envelope: ");
            envelopeAmountInput = readNumber();
        } while(envelopeAmountInput < 1);
        return envelopeAmountInput;
    }
    /**
     * Delivers money to the cardholder
     * @param amount the amount of $dollars to pay out
     */
    void deliverMoney(int amount){
        System.out.println("Here is $" + amount);
    }
    /**
     * Asks user how much they want to withdraw
     * @return requested amount to withdraw
     */
    int readWithdrawalAmount(){
        System.out.println("How much would you like to withdraw?");
        int withdrawalInput = 0;
        while (withdrawalInput < 1){
            displayMessage("Please enter positive whole dollar amount to withdraw:");
            withdrawalInput = readNumber();
            displayMessage("Amount to withdraw: $" + withdrawalInput);
        }
        return withdrawalInput;
    }
    /**
     * Prof. Halperin or Prateek, Prof. Halperin's javadoc has no idication of what this does
     * @return beats me, but I'll try to stop by office hours and ask how to use it
     */
    private int getAmount(){
        return 0;
    }
    /**
     * prints statement
     * @param message to be displayed
     */
    void displayMessage(String message){
        System.out.println(message);
    }
    /**
     * prints statement emphatically
     *
     * @param errorMessage to be displayed
     */
    void displayError(String errorMessage){
        System.out.println(errorMessage);
    }
    String getQuitString(){
        return QUIT_STRING;
    }
}

