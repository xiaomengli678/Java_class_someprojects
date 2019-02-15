/**
*@version cs251 Lab002 Date: 09/18/2018
*@author Xiaomeng Li
**/

/**
*This is the class IceCream extending parent class Dessert
*It will return the price of IceCream
**/

public class IceCream extends Dessert {
    public Double iceCreamPrice;


    public IceCream(String name, double price){
    	/**
        *@param IceCream's String name, double price
        *@return not really returing anything, just assign
        *        price as well as name to the IceCream class
        **/
        super(name);
        this.iceCreamPrice = price;
    }

    @Override
    public double getPrice() {
    	/**
        *@param nothing
        *@return Ice Cream's price
        **/
        return this.iceCreamPrice;
    }
}
