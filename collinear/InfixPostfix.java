/*
Output:
( 3 + 4 )

3 4 +

( 10 / ( 2 + 3 ) )

10 2 3 +

( 100 / ( ( 4 - 2 ) * 25 ) )

100 4 2 - 25 *
*/

import edu.princeton.cs.algs4.Stack;

import java.util.Scanner;

public class InfixPostfix {
    /*public static int Precedence(char str) {
        if (str == '+' || str == '-') {
            return 0;
        }

        if (str == '*' || str == '/') {
            return 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int i = 0;
        while (i != input.length()) {
            char str = input.charAt(i);

            if (str == '(') {
                stack.push(str);
            }

            else if (str == ')') {
                while (stack.peek() != '(') {
                    System.out.print(stack.pop() + " ");
                }
            }

            else if ((str == '+' || str == '-')) {
                if (!stack.isEmpty()) {
                    while (Precedence(str) < Precedence(stack.peek()) || stack.peek() != '(') {
                        System.out.print(stack.pop() + " ");
                    }
                }
                stack.push(str);
            }

            else if ((str == '*' || str == '/')) {
                if (!stack.isEmpty()) {
                    while (Precedence(str) < Precedence(stack.peek()) || stack.peek() != '(') {
                        System.out.print(stack.pop() + " ");
                    }
                }

                stack.push(str);
            }

            else {
                System.out.print(str);
            }
            i++;
        }
        scanner.close();
    }*/
    public static void main(String[] args) {
        System.out.println("( 3 + 4 ) = " + convert("( 3 + 4 )"));
        System.out.println("( 10 / ( 2 + 3 ) ) = " + convert("( 10 / ( 2 + 3 ) )"));
        System.out.println(
                "( 100 / ( ( 4 - 2 ) * 25 ) ) = " + convert("( 100 / ( ( 4 - 2 ) * 25 ) )"));
    }

    public static String convert(String expression) {
        Stack<String> stack = new Stack<String>();
        String result = "";
        Scanner scan = new Scanner(expression);
        while (scan.hasNext()) {
            String str = scan.next();
            if (str.equals("("))
                stack.push(str);
            else if (str.equals(")")) {
                if (!stack.isEmpty()) {
                    String symbol = String.valueOf(stack.pop());
                    while (!symbol.equals("(") && !stack.isEmpty()) {
                        result += symbol + " ";
                        symbol = String.valueOf(stack.pop());
                    }
                }
            }
            else if (str.equals("*") || str.equals("/")) {
                if (!stack.isEmpty()) {
                    String symbol = String.valueOf(stack.peek());
                    while ((!symbol.equals("+") || !symbol.equals("-")) && !symbol.equals("(")) {
                        result += stack.pop() + " ";
                        if (!stack.isEmpty())
                            symbol = String.valueOf(stack.peek());
                    }
                }
                stack.push(str);
            }
            else if (str.equals("+") || str.equals("-")) {
                if (!stack.isEmpty()) {
                    String symbol = String.valueOf(stack.peek());
                    while (!symbol.equals("(")) {
                        result += stack.pop() + " ";
                        if (!stack.isEmpty())
                            symbol = String.valueOf(stack.peek());
                    }
                }
                stack.push(str);
            }
            else
                result += str + " ";
        }
        while (!stack.isEmpty())
            result += String.valueOf(stack.pop()) + " ";
        return result;
    }
}

