import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a scanner from standard input
        Scanner scanner = new Scanner(System.in);

        // Make a new calculator
        PostfixCalculator calc = new PostfixCalculator();

        // keep reading tokens until we run out.
        while(scanner.hasNext()) {
            if(scanner.hasNextDouble()) {
                // Next item is a double, so must be an operand
                calc.storeOperand(scanner.nextDouble());
            } else {
                // otherwise, we have an operator
                calc.evaluateOperator(scanner.next());
            }
        }
    }

}


/**public class Main {

    public static void main(String[] args) {
        Stack<Double> s1 = new StackOfDoubles();
        Stack<Double> s2 = new StackOfDoubles();

        s1.push(1.2);
        s1.push(3.4);
        s1.push(5.6);
        s1.push(7.8);

        System.out.println(s1.peek());

        System.out.println("s1 empty? " + s1.isEmpty());
        System.out.println("s2 empty? " + s2.isEmpty());

        while(!s1.isEmpty()) {
            double val = s1.pop();
            System.out.println(val);
            s2.push(val);
        }
        System.out.println("s1 empty? " + s1.isEmpty());
        System.out.println("s2 empty? " + s2.isEmpty());
        while(!s2.isEmpty()) {
            System.out.println(s2.pop());
        }
    }
}
**/