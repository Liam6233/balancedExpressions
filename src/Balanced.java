import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
//Author: Liam Curley
//Balanced.java: checks a number of expressions in a text file to make sure the syntax is balanced
//terms used:
//"expression" - each individual line in the text file
//"brackets" - refers to all 3 types of brackets (), {}, []
public class Balanced
{
    private ArrayStack stack; //the stack used to check balance of open and closed brackets
    private int numCases; //the number of expressions that need to be checked in the text file, set bsed on number at the top of the file
    Scanner s; //scanner used to read the text file
    public static void main(String[] args) throws FileNotFoundException
    {
        Balanced b = new Balanced();
        System.out.println(b.numBalanced());
    }
    //default constructor for Balanced.java
    //sets the scanner to read our text file "in.txt", numCases becomes the number at the top of the text file
    //stack gets assigned to be a new ArrayStack based on my implementation of stack
    public Balanced() throws FileNotFoundException
    {
        s = new Scanner(new File("in.txt"));
        numCases = s.nextInt();
        s.nextLine();
        stack = new ArrayStack();
    }
    //method that checks the number of balanced statements in the txt file
    //returns an int that represents the number of balanced statements
    public int numBalanced()
    {
        String line = s.nextLine(); //the string that represents each expression
        int amount = 0; //count of how many expression are balanced
        Boolean balanced = false; //used to check if statement is balanced. starts false and becomes true if the statement is balanced
        for(int j = 0; j < numCases; j++) //using a nested for loop that goes through each line, then each character in the line
        {
            for (int i = 0; i < line.length(); i++)
            {
                //first 3 if statements check if the current character is an open bracket. if so, the bracket is pushed into the stack
                //the continues are there to speed up the process since once it pushes to the stack, it does not need to check the same character for every other type of bracket
                if (line.charAt(i) == '(')
                {
                    stack.push('(');
                    continue;
                }
                if (line.charAt(i) == '{')
                {
                    stack.push('{');
                    continue;
                }
                if (line.charAt(i) == '[')
                {
                    stack.push('[');
                    continue;
                }
                /*
                these 3 if statements check for closed brackets. the stack must not be empty so it checks for that first
                then if the character is a close bracket, the top of the stack must be an open bracket. if the statement is true
                the stack is popped removing the top element and the boolean balanced is set to true
                if the stack is empty and it the current char is one of the closed brackets, that means there is no open bracket to correspond to
                meaning the expression is unbalanced and it sets balanced to false. this is to make sure even if some brackets match up, if only a single one does not
                it still counts it as unbalanced.
                 */
                if(stack.peek() != null && line.charAt(i) == ')' && (char) stack.peek() == '(')
                {
                    stack.pop();
                    balanced = true;
                }
                else if(stack.peek() == null && line.charAt(i) == ')')
                {
                    balanced = false;
                }
                if(stack.peek() != null &&line.charAt(i) == '}'&& (char) stack.peek() == '{')
                {
                    stack.pop();
                    balanced = true;
                }
                else if(stack.peek() == null && line.charAt(i) == '}')
                {
                    balanced = false;
                }
                if(stack.peek() != null &&line.charAt(i) == ']'&& (char) stack.peek() == '[') {
                    stack.pop();
                    balanced = true;
                }
                else if(stack.peek() == null && line.charAt(i) == ']')
                {
                    balanced = false;
                }

            }
            // in the outer loop. after going through all the characters in the expression, if it is balanced, the stack should be empty and the balanced boolean should be true
            //if true, amount increases by 1.
            if (stack.empty() && balanced)
            {
                amount++;
            }
            //if the stack is not empty, that means the expression is unbalanced. but before checking the next expression
            //the program has to reset the stack so there are no leftover open brackets, and it has to reset balanced to begin as false
            while(stack.peek() != null)
            {
                stack.pop();
            }
            balanced = false;
            //after resetting the stack and balanced. it moves onto the next line in the text file. it is in a try catch loop
            //so that it doesn't create an error when it tries to check for the line after the final expression.
            try
            {
                line = s.nextLine();
            }
            catch(Exception e)
            {
                line = "";
            }
        }
        return amount; //amount is returned
    }
}
