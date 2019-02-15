/**
*@version cs251 Lab002 Date: 10/02/2018
*@author Xiaomeng Li
**/
import java.util.*;
/**
This is the public PostfixCalculator class. Basically this class 
defines Stack as well as operatorMap. Operands Stack is used for storing 
the numbers from user input and operatorMap is used to store the five 
different operators: + - * / =.
**/

public class PostfixCalculator {
    private Stack<Double> operands = new StackOfDoubles();
    private Map<String, Operator> operatorMap = new HashMap<>();
    /** Class operatoradd implements Operator interface and defines 
    add as two arguments as well as how to evaluate adding two numbers
    **/
    private static class operatoradd implements Operator {
        /** tell number of arguments requied for this operator to implement **/
        public int numArgs(){
            /**
            @return number of arguments required to implement this operator.
            **/
            return 2;
        }
        /**eval method to return the operation adding's results **/
        public double eval(List<Double> args){
            /**
            @param args in a list
            @return the results after evaluation
            **/
            return args.get(0) + args.get(1);
        }
    }
    /** Class operatorsub implements Operator interface and defines 
    subtraction as two arguments as well as how to evaluate 
    subtracting two numbers
    **/
    private static class operatorsub implements Operator {
        /** tell number of arguments requied for this operator to implement **/
        public int numArgs(){
            /**
            @return number of arguments required to implement this operator.
            **/
            return 2;
        }
        /**eval method to return the operation subtracting's results **/
        public double eval(List<Double> args){
            /**
            @param args in a list
            @return the results after evaluation
            **/
            return args.get(1) - args.get(0);
        }
    }
    /** Class operatorprod implements Operator interface and defines 
    multiply as two arguments as well as how to evaluate 
    multiplying two numbers
    **/
    private static class operatorprod implements Operator {
        /** tell number of arguments requied for this operator to implement **/
        public int numArgs(){
            /**
            @return number of arguments required to implement this operator.
            **/
            return 2;
        }
        /**eval method to return the operation multiplying's results **/
        public double eval(List<Double> args){
            /**
            @param args in a list
            @return the results after evaluation
            **/
            return args.get(0) * args.get(1);
        }
    }
    /** Class operatorquo implements Operator interface and defines 
    dividing as two arguments as well as how to evaluate 
    dividing two numbers
    **/
    private static class operatorquo implements Operator {
        /** tell number of arguments requied for this operator to implement **/
        public int numArgs(){
            /**
            @return number of arguments required to implement this operator.
            **/
            return 2;
        }
        /**eval method to return the operation dividing's results **/
        public double eval(List<Double> args){
            /**
            @param args in a list
            @return the results after evaluation
            **/
            return args.get(1) / args.get(0);
        }
    }
    /** Class operatoreql implements Operator interface and defines 
    equal as one argument as well as how to evaluate 
    getting the number after using equal
    **/
    private static class operatoreql implements Operator {
        /** tell number of arguments requied for this operator to implement **/
        public int numArgs(){
            /**
            @return number of arguments required to implement this operator.
            **/
            return 1;
        }
        /**eval method to return the operation eql's results **/
        public double eval(List<Double> args){
            /**
            @param args in a list
            @return the results after evaluation
            **/
            System.out.println(args.get(0));
            return args.get(0);
        }
    }
    /**
    PostfixCalculator() logic:
    Just ask the default constructor to
    put new operators' strings as well as their new class
    instances into the HashMap.
    **/
    public PostfixCalculator() {
        operatorMap.put("+",new operatoradd());
        operatorMap.put("add",new operatoradd());
        operatorMap.put("-",new operatorsub());
        operatorMap.put("sub",new operatorsub());
        operatorMap.put("*",new operatorprod());
        operatorMap.put("mult",new operatorprod());
        operatorMap.put("/",new operatorquo());
        operatorMap.put("div",new operatorquo());
        operatorMap.put("=",new operatoreql());
        operatorMap.put("print",new operatoreql());
    }
    /**
    When user input a new operand, use this method 
    to store the new number into the stack.
    **/
    public void storeOperand(Double operand){
        /**
        @param operand
        **/
        operands.push(operand);
    }
    /**
    evaluateOperator basic logic:
    Basically, when a operator is received, first the 
    program should go to the HashTable to find which operator it is and 
    how many arguments are needed in order to implement this operator. 
    After getting to know the number of operands, we extract the 
    required numbers from stack and put them into a list. The sequence is 
    kind of tricky since the numbers come out of a stack and go into 
    directly a list.after evaluation. Finally, we put the new result into
    the stack again.
    **/
    public void evaluateOperator(String operator){
        /**
        @param operator
        **/
        if (operatorMap.containsKey(operator)){
            int numberOfOperands = operatorMap.get(operator).numArgs();
            int i = 0;
            List<Double> tempList = new ArrayList<>();
            while(i<numberOfOperands){
                i = i + 1;
                tempList.add(operands.pop());

            }
            Double res = operatorMap.get(operator).eval(tempList);
            operands.push(res);
        }

    }
}
