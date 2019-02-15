/**
*@version cs251 Lab002 Date: 10/02/2018
*@author Xiaomeng Li
**/
import java.util.*;
/**
This is the StackOfDoubles class which implements Stack.
It uses the data structure: Linked List to define a new 
structure: stack with all the pop, push, peek, isEmpty functions
**/
public class StackOfDoubles implements Stack<Double>{
    
    public Node first;
    /**
    This is defining the basic structure of a linkedList. 
    Node, address to the next Node
    **/
    public class Node {
        private Double value;
        private Node next;
    }
    /**
    Find if the stack is empty.
    If the first node is empty, the whole linked list is also empty
    **/
    public boolean isEmpty(){
        /**
        @return boolean isEmpty status
        **/
        return first == null;
    }
    /**
    Stack push function: When a new value is available, put the old 
    first head node of the linked list into oldFirst Node, then put 
    new value to first, direct first to oldFirst's address
    **/
    public void push(Double newValue){
        /**
        @param newValue to add to the stack
        **/
        Node oldFirst = first;
        first = new Node();
        first.value = newValue;
        first.next = oldFirst;
    }
    /**
    find the top value of the current Stack. Remember to direct the 
    first value to the next after poping
    **/
    public Double pop(){
        /**
        @return topValue
        **/
        if (isEmpty()){
            throw new NoSuchElementException("Stack is empty");
        }
        Double topValue = first.value;
        first = first.next;
        return topValue;

    }
    /**
    find the head value of the stack.
    Remember not to direct the first value to the next after peeking
    **/
    public Double peek(){
        /**
        @return topValue of the current Stack.
        **/
        Double topValue = first.value;
        return topValue;
    }
}
