/**
*@version cs251 Lab002 Date: 09/25/2018
*@author Xiaomeng Li
**/

/**
This is the InsufficientFundsException class which extends the 
exception to create an exception class designed for the bank. When 
shortfall is delivered, the computer will throw out an exception
message and store this shortfall number as a positive number.
**/
public class InsufficientFundsException extends Exception {
    double shortfall;

    public InsufficientFundsException(double shortfall){
    	/**
        @param shortfall = accountBalance - amount when amount is bigger
               than accountBalance
        @return not returning anything. Just store the shortfall value
                and call super class.
        **/
        super("You need more money!");
        this.shortfall = 0-shortfall;

    }

    public double getShortfall(){
    	/**
        @param Nothing
        @return the absolute value of shortfall.
    	**/
        return this.shortfall;

    }

}
