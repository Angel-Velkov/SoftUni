package Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HouseParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<String> names = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            String[] inputAsArr = input.split("\\s+");
            String name = inputAsArr[0];

            if (inputAsArr[1].equals("is") && inputAsArr[2].equals("going!")) {
                checkingForRepeatedName(names, name);
            } else {
                removingNamesFromTheList(names, name);
            }
        }

        for (String s : names) {
            System.out.println(s);
        }
    }

    private static void removingNamesFromTheList(List<String> names, String name) {
        if (!names.remove(name)) {
            System.out.println(name + " is not in the list!");
        } else {
            names.remove(name);
        }
    }

    private static void checkingForRepeatedName(List<String> names, String name) {
        boolean theNameIsRepeated = false;
        for (int i = 0; i < names.size(); i++) {
            if (name.equals(names.get(i))) {
                System.out.println(name + " is already in the list!");
                theNameIsRepeated = true;
                break;
            }
        }
        if (!theNameIsRepeated) {
            names.add(name);
        }
    }

}
