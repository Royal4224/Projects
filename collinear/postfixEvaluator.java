/* Output
7
2
2
 */


import edu.princeton.cs.algs4.Stack;

import java.util.Scanner;

public class postfixEvaluator {
    public static int evaluate(String expression) {
        Stack<Integer> stack = new Stack<>();
        Scanner scan = new Scanner(expression);
        int firstNum, secondNum, result = 0;
        String current = "";
        while (scan.hasNext()) {
            current = scan.next();
            if (current.charAt(0) == '+' || current.charAt(0) == '-' || current.charAt(0) == '*'
                    || current.charAt(0) == '/') {
                firstNum = stack.pop();
                secondNum = stack.pop();
                result = evalOp(current.charAt(0), firstNum, secondNum);
                stack.push(result);
            }
            else {
                stack.push(Integer.parseInt(current));
            }
        }
        return result;
    }

    private static int evalOp(char operand, int firstNum, int secondNum) {

        if (operand == '+')
            return firstNum + secondNum;

        if (operand == '-')
            return secondNum - firstNum;

        if (operand == '*')
            return firstNum * secondNum;

        if (operand == '/')
            return secondNum / firstNum;


        return 0;
    }

    public static void main(String[] args) {
        System.out.println(postfixEvaluator.evaluate(InfixPostfix.convert("3 + 4")));
        System.out.println(postfixEvaluator.evaluate(InfixPostfix.convert("10 / ( 2 + 3 )")));
        System.out.println(
                postfixEvaluator.evaluate(InfixPostfix.convert("100 / ( ( 4 - 2 ) * 25 )")));
    }
}
