/**
*@version cs251 Lab002 Date: 09/18/2018
*@author Xiaomeng Li
**/

/**
* class Sundae extends IceCream class and return
* total amount of money for ice cream and dessert
**/
public class Sundae extends IceCream {

    public Double iceCreamPrice;
    public Double dessertPrice;

    public Sundae(IceCream iceCream, Dessert dessert) {
        /**
        *@param IceCream Instance and Dessert Instance
        *@return not really returing anything, just assigning
        *        ice cream price as well as dessert price to the Sundae class
        **/
        super((iceCream.name+ " topped with " + dessert.name), 
            iceCream.getPrice());
        this.iceCreamPrice = iceCream.getPrice();
        this.dessertPrice = dessert.getPrice();
    }

    @Override
    public double getPrice() {
        /**
        *@param nothing
        *@return ice cream price plus dessert price
        **/
        return this.iceCreamPrice + this.dessertPrice;
    }


}


