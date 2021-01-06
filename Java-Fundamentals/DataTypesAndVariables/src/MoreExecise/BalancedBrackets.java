package MoreExecise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BalancedBrackets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<Boolean> brackets = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();

            if (input.equals("(")) {
                brackets.add(false);
            } else if (input.equals(")")) {
                boolean isNotFounded = true;

                for (int j = 0; j < brackets.size(); j++) {
                    if (!brackets.get(j)) {
                        brackets.set(j, true);
                        isNotFounded = false;
                        break;
                    }
                }
                if (isNotFounded) {
                    System.out.println("UNBALANCED");
                    return;
                }
            }
        }
        if (brackets.contains(false)) {
            System.out.println("UNBALANCED");
        } else {
            System.out.println("BALANCED");
        }
    }
}
