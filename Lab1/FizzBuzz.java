/**
 * @version cs251 Lab 002 date: 2018-08-31
 * @author Xiaomeng Li
 * This is the Lab 1 FizzBuzz
 * To use it, in terminal type:
 *                            javac FizzBuzz.java
 * After compiling, type:     java FizzBuzz 3 5 100 
 */

/** This is the public class FizzBuzz
 * FizzBuzz Game explaination:
 * Three parameters are given to the program: firstVal, secondVal, thirdVal.
 * The program will take each number from one to thirdVal,
 * and make this number divided by firstVal and secondVal.
 * If the number is divided exactly by firstVal only, print out "Fizz".
 * If the number id divided exactly by secondVal only, print out "Buzz".
 * If the number is divided exactly by both firstVal and secondVal, then 
 * print out "FizzBuzz".
 * Otherwise the program will just print out the number itself.
 * Basically the public class FizzBuzz implements the four cases 
 * of different user inputs described above
 */
public class FizzBuzz{
    /** This is the public static main function,
     * written for the public class FizzBuzz. No return value required
     * @param three parameters: firstVal and secondVal become game's divisors.
     * thirdVal becomes game's largest number to be divided.
     */
    public static void main(String[] args){
	// three inputs required in main
        if(args.length != 3){
	    System.err.println("Expected 2 arguments, but got " + args.length);
	} else {
		// transfer the three inputs into int
	      int firstVal = Integer.parseInt(args[0]);
              int secondVal = Integer.parseInt(args[1]);
              int thirdVal = Integer.parseInt(args[2]);

              for(int i=1; i<=thirdVal; i++){
                  if(i%firstVal == 0 && i%secondVal != 0){
                      System.out.println("Fizz");
                  } 
                  else if (i%firstVal != 0 && i%secondVal == 0){
		      System.out.println("Buzz"); 
                  }
                  else if (i%firstVal == 0 && i%secondVal == 0){
                      System.out.println("FizzBuzz");
                  }
                  else {
		      System.out.println(i);
                  }
              }
        }
    }
}