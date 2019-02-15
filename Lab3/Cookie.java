/**
*@version cs251 Lab002 Date: 09/18/2018
*@author Xiaomeng Li
**/

/**
*The class Cookie extends Dessert as parent and then 
*returning the number of item and price as well as the total
*money needed for the Cookie
**/
public class Cookie extends Dessert {

    public int getItemCount;
    public Double getPricePerDozen;

    public Cookie(String name, int item, double price){
        /**
        *@param Cookie's String name, double price, int item
        *@return not really returing anything, just assign item, 
        *        price as well as name to the Cookie class
        **/

        super(name);
        this.getItemCount = item;
        this.getPricePerDozen = price;
    }
    public int getItemCount(){
        /**
        *@param nothing
        *@return getItemCount(), actually number of item
        **/
        return this.getItemCount;
    }
    public double getPricePerDozen(){
        /**
        *@param nothing
        *@return getPricePerDozen(), actually price
        **/
        return this.getPricePerDozen;
    }
    @Override
    public double getPrice() {
        /**
        *@param nothing
        *@return getItemCount multiplied by getPricePerDozen/12 to get the 
                final amount of money
        **/
        return this.getItemCount * this.getPricePerDozen / 12;
    }
}
