package Exercise;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InfixToPostfix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<String> outputQueue = new ArrayDeque<>();
        ArrayDeque<String> operatorsStack = new ArrayDeque<>();
        Map<String, Integer> priorities = new HashMap<>();
        priorities.put("*", 2);
        priorities.put("/", 2);
        priorities.put("+", 1);
        priorities.put("-", 1);
        priorities.put("(", -1);

        String[] tokens = scanner.nextLine().split("\\s+");
        for (String token : tokens) {
            switch (token) {
                case "+":
                case "-":
                case "*":
                case "/":
                    while (!operatorsStack.isEmpty() && priorities.get(operatorsStack.peek()) >= priorities.get(token)) {
                        outputQueue.offer(operatorsStack.pop());
                    }
                    operatorsStack.push(token);
                    break;
                case "(":
                    operatorsStack.push("(");
                    break;
                case ")":
                    while (!operatorsStack.isEmpty() && !operatorsStack.peek().equals("(")) {
                        outputQueue.offer(operatorsStack.pop() + "");
                    }
                    operatorsStack.pop();
                    break;
                default:
                    outputQueue.offer(token);
            }
        }
        while (!operatorsStack.isEmpty()) {
            outputQueue.offer(operatorsStack.pop());
        }
        for (String s : outputQueue) {
            System.out.print(s + " ");
        }
    }
}
