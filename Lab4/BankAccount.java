/**
*@version cs251 Lab002 Date: 09/25/2018
*@author Xiaomeng Li
**/

/**
This is the BankAccount class. This class is created for 
storing the bank account_ID, storing accountBalance; Besides,
people could also deposit and withdraw the money. With withdrwaing,
if accountBalance - amount < 0, the computer will throw an exception
and the amount of accountBalance will not change.
**/
public class BankAccount {
    int accountID;
    double accountBalance = 0;

    public BankAccount(int account_ID){
        /**
        @param account_ID, the number to store
        @return Nothing.
        **/
        this.accountID = account_ID;
    }

    public int getAccountID(){
        /**
        @param Nothing
        @return accountID
        **/
        return this.accountID;

    }

    public double getAccountBalance(){
        /**
        @param Nothing
        @return accountBalance
        **/
        return this.accountBalance;
    }

    public void deposit (double amount) {
        /**
        @param amount
        @return Nothing. Just to increase the accountBalance for the deposit
        **/
        this.accountBalance = this.accountBalance + amount;
    }

    public void withdraw (double amount) throws InsufficientFundsException{
        /**
        @param amount
        @return Nothing. Just to compare the accountBalance with amount.If the 
               amount is larger than accountBalance, throw an exception 
               and refuse to change the balance.
        **/

        if (this.accountBalance - amount < 0) {

            throw new InsufficientFundsException((this.accountBalance - amount));

        } else {
            this.accountBalance = this.accountBalance - amount;
        }


    }

}

