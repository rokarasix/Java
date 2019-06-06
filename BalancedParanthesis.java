package programs;

import java.util.Stack;


public class BalancedParanthesis {

    public static void main(String[] args) {

        BalancedParanthesis b=new BalancedParanthesis();
        String s="{()[}]";
        System.out.println(" "+b.check(s));
    }


    private boolean check(String s) {

        Stack<Character> stack=new Stack<Character>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='{' || s.charAt(i)=='(' || s.charAt(i)=='['){
                stack.push(s.charAt(i));
            }
            else if(s.charAt(i)=='}' && stack.peek()=='{' )
            {
               stack.pop();
            }
            else if(s.charAt(i)==')' && stack.peek()=='(' )
            {
                stack.pop();
            }
            else if(s.charAt(i)==']' && stack.peek()=='[' )
            {
                stack.pop();
            }

        }
         if(stack.isEmpty())
        return true;
         else
             return false;
    }

}
