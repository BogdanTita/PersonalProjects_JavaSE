import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static int precedence (char c){
        if(c == '+' || c == '-'){
            return 1;
        }else if(c == '*' || c == '/'){
            return 2;
        }else if(c == '^'){
            return 3;
        }else{
            return -1;
        }
    }

    public static boolean isLeftParenthesis(char c){
        return c == '(' || c == '{' || c == '[';
    }

    public static boolean isRightParenthesis(char c){
        return c == ')' || c == '}' || c == ']';
    }

    public static boolean isLeftAssociative(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression = "3+(2+1)*2^3^2-8/(5-1*2/2)";

        StringBuilder postFixed = new StringBuilder();
        Deque<Character> operators = new LinkedList<>();
        Deque<Integer> stackResult = new LinkedList<>();

        for(int i = 0; i < expression.length(); i++){
            char c = expression.charAt(i);

            if(Character.isDigit(c)){
                postFixed.append(c);
                continue;
            }

            if(isLeftParenthesis(c)){
                operators.push(c);
                continue;
            }

            if(isOperator(c)){
                while(!operators.isEmpty() &&
                        !isLeftParenthesis(operators.peek()) &&
                        (precedence(c) < precedence(operators.peek()) ||
                        ( precedence(c) == precedence(operators.peek()) && isLeftAssociative(operators.peek())))){
                    postFixed.append(operators.pop());
                }

                operators.push(c);
                continue;
            }

            if(isRightParenthesis(c)){
                while(!operators.isEmpty() && !isLeftParenthesis(operators.peek())){
                    postFixed.append(operators.pop());
                }
                if(operators.isEmpty()){
                    System.err.println("Invalid expression");
                    System.exit(-1);
                }else{
                    operators.pop();
                }
            }
        }

        while(!operators.isEmpty()){
            if(isLeftParenthesis(operators.peek())){
                System.err.println("Invalid expression");
                System.exit(-1);
            }

            postFixed.append(operators.pop());
        }

        System.out.println(postFixed);

        for(int i = 0; i < postFixed.length(); i++){
            char c = postFixed.charAt(i);

            if(Character.isDigit(c)){

                int number = Character.getNumericValue(c);
                stackResult.push(number);

            }else{
                Integer op1 = stackResult.poll(); // folosesc poll pt ca returneaza NULL pe deque gol, in loc de pop, care arunca exceptie
                if(op1 == null){
                    System.err.println("Error: postfixed expression is incorrect");
                    System.exit(-1);
                }

                Integer op2 = stackResult.poll();
                if(op2 == null){
                    System.err.println("Error: postfixed expression is incorrect");
                    System.exit(-1);
                }

                Integer opResult;

                switch (c){
                    case '+' :
                        opResult = op2 + op1;
                        stackResult.push(opResult);
                        break;
                    case '-' :
                        opResult = op2 - op1;
                        stackResult.push(opResult);
                        break;
                    case '*' :
                        opResult = op2 * op1;
                        stackResult.push(opResult);
                        break;
                    case '/' :
                        opResult = op2 / op1;
                        stackResult.push(opResult);
                        break;
                    default:
                        opResult = (int)Math.pow(op2, op1);
                        stackResult.push(opResult);
                        break;
                }
            }
        }

        int result = stackResult.poll();
        if(!stackResult.isEmpty()){
            System.err.println("Error: postfixed expression is incorrect");
        }else{
            System.out.println(expression + " = " + result);
        }

    }
}