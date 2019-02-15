/**
*@version cs251 Lab002 Date: 09/18/2018
*@author Xiaomeng Li
**/

/**
*The class Candy extends Dessert as parent and then 
*returning the weight and price as well as the total
*money needed for the Candy
**/
public class Candy extends Dessert{

    public Double getWeightInPounds;
    public Double getPricePerPound;

    public Candy(String name, double weight, double price){
    /**
    *@param Candy's String name, double weight, double price
    *@return not really returing anything, just assign weight, 
    *        price as well as name to the Candy class
    **/
        super(name);
        this.getWeightInPounds = weight;
        this.getPricePerPound = price;
    }
    public double getWeightInPounds(){
        /**
        *@param nothing
        *@return getWeightInPounds, actually returning weight
        **/
        return this.getWeightInPounds;
    }
    public double getPricePerPound(){
        /**
        *@param nothing
        *@return getPricePerPound, actually returning price
        **/
        return this.getPricePerPound;
    }
    @Override
    public double getPrice() {
        /**
        *@param nothing
        *@return the result of multiplying getWeightInPounds 
        *        with getPricePerPound
        **/
        return this.getWeightInPounds * this.getPricePerPound;
    }

}