/*
 * Programmer: Sanskar Aryal
 * Class: Cosc 3355
 * Date: 02/05/2020
 */
public class Stack  {
  final int MAX_STACK = 1024;  // maximum size of stack
 // private Object items[];
  private int top;

  public Stack() {
   // items = new Object[MAX_STACK]; 
    top = 1024; 
  }  // end default constructor

  public boolean isEmpty() {
    return top > 1023;
  }  // end isEmpty

  public boolean isFull() {
    return top == MAX_STACK+1;
  }  // end isFull

  public void push(Object newItem)  {
    if (!isFull()) {
      Memory.location[--top] = newItem.toString();
    } 
    else {
      System.out.println("StackException on " +
                               "push: stack full");
    }  // end if
  }  // end push

  public Object pop() {
    if (!isEmpty()) {
    	
      return Memory.location[top++];
    }
    else {
      System.out.println("StackException on " +
                               "pop: stack empty");
      return Memory.location[top++];
    }  // end if
  }  // end pop

  public Object peek() {
    if (!isEmpty()) {
      return Memory.location[top];
    }
    else {
      System.out.println("Stack exception on " +
                               "peek - stack empty");
      return Memory.location[top++];
    }  // end if
  }  // end peek
}  // end StackArrayBased
