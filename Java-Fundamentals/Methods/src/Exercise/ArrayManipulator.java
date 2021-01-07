package Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayManipulator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] numbersAsString = scan.nextLine().split(" ");
        int[] numArray = new int[numbersAsString.length];
        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = Integer.parseInt(numbersAsString[i]);
        }
        String input = scan.nextLine();

        while (!input.equals("end")) {
            String[] commandArr = input.split(" ");
            String command = commandArr[0];

            switch (command) {
                case "exchange":
                    int index = Integer.parseInt(commandArr[1]);

                    if (index >= 0 && index < numArray.length) {
                        exchangeArrayNumbers(numArray, index);
                    } else {
                        System.out.println("Invalid index");
                    }
                    break;
                case "max":
                    if (commandArr[1].equals("even")) {
                        printIndexAtMaxEvenNumber(numArray);
                    } else if (commandArr[1].equals("odd")) {
                        printIndexAtMaxOddNumber(numArray);
                    }
                    break;
                case "min":
                    if (commandArr[1].equals("even")) {
                        printIndexAtMinEvenNumber(numArray);
                    } else if (commandArr[1].equals("odd")) {
                        printIndexAtMinOddNumber(numArray);
                    }
                    break;
                case "first":
                    int firstCount = Integer.parseInt(commandArr[1]);

                    if (firstCount > numArray.length) {
                        System.out.println("Invalid count");
                    } else if (commandArr[2].equals("even")) {
                        printACountOfTheFirstEvenNumbers(numArray, firstCount);
                    } else if (commandArr[2].equals("odd")) {
                        printACountOfTheFirstOddNumbers(numArray, firstCount);
                    }
                    break;
                case "last":
                    int lastCount = Integer.parseInt(commandArr[1]);

                    if (lastCount > numArray.length) {
                        System.out.println("Invalid count");
                    } else if (commandArr[2].equals("even")) {
                        printACountOfTheLastEvenNumbers(numArray, lastCount);
                    } else if (commandArr[2].equals("odd")) {
                        printACountOfTheLastOddNumbers(numArray, lastCount);
                    }
                    break;
            }


            input = scan.nextLine();
        }
        System.out.println(Arrays.toString(numArray));

    }

    static void exchangeArrayNumbers(int[] arr, int index) {
        index += 1;
        //[1, 2, 3, 4, 5] -> exchange 2 -> result: [4, 5, 1, 2, 3]

        int[] firstArr = new int[index];
        int[] secondArr = new int[arr.length - index];

        for (int i = 0; i < index; i++) {
            firstArr[i] = arr[i];
        }
        for (int i = index; i < arr.length; i++) {
            secondArr[i - index] = arr[i];
        }

        for (int i = 0; i < secondArr.length; i++) {
            arr[i] = secondArr[i];
        }

        for (int i = 0; i < firstArr.length; i++) {
            arr[i + secondArr.length] = firstArr[i];
        }
    }

    static void printIndexAtMaxEvenNumber(int[] arr) {
        int maxEvenNum = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0 && arr[i] >= maxEvenNum) {
                maxEvenNum = arr[i];
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("No matches");
        } else {
            System.out.println(index);
        }
    }

    static void printIndexAtMaxOddNumber(int[] arr) {
        int maxOddNum = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0 && arr[i] >= maxOddNum) {
                maxOddNum = arr[i];
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("No matches");
        } else {
            System.out.println(index);
        }
    }

    static void printIndexAtMinEvenNumber(int[] arr) {
        int minEvenNum = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0 && arr[i] <= minEvenNum) {
                minEvenNum = arr[i];
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("No matches");
        } else {
            System.out.println(index);
        }
    }

    static void printIndexAtMinOddNumber(int[] arr) {
        int minOddNumber = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0 && arr[i] <= minOddNumber) {
                minOddNumber = arr[i];
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("No matches");
        } else {
            System.out.println(index);
        }
    }

    static void printACountOfTheFirstEvenNumbers(int[] arr, int count) {
        String allNumbersAsString = "";
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0 && counter < count) {
                allNumbersAsString += arr[i] + " ";
                counter++;
            }
        }
        if (allNumbersAsString.equals("")) {
            System.out.println("[]");
        } else {
            String[] evenNumbersArrAsString = allNumbersAsString.split(" ");
            int[] evenNumbersArr = new int[evenNumbersArrAsString.length];
            for (int i = 0; i < evenNumbersArr.length; i++) {
                evenNumbersArr[i] = Integer.parseInt(evenNumbersArrAsString[i]);
            }
            System.out.println(Arrays.toString(evenNumbersArr));
        }
    }

    static void printACountOfTheFirstOddNumbers(int[] arr, int count) {
        String allNumbersAsString = "";
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0 && counter < count) {
                allNumbersAsString += arr[i] + " ";
                counter++;
            }
        }
        if (allNumbersAsString.equals("")) {
            System.out.println("[]");
        } else {
            String[] oddNumbersArrAsString = allNumbersAsString.split(" ");
            int[] oddNumbersArr = new int[oddNumbersArrAsString.length];
            for (int i = 0; i < oddNumbersArr.length; i++) {
                oddNumbersArr[i] = Integer.parseInt(oddNumbersArrAsString[i]);
            }
            System.out.println(Arrays.toString(oddNumbersArr));
        }

    }

    static void printACountOfTheLastEvenNumbers(int[] arr, int count) {
        String allNumbersAsString = "";
        int counter = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] % 2 == 0 && counter < count) {
                allNumbersAsString += arr[i] + " ";
                counter++;
            }
        }
        if (allNumbersAsString.equals("")) {
            System.out.println("[]");
        } else {
            String[] evenNumbersArrAsString = allNumbersAsString.split(" ");
            int[] evenNumbersArr = new int[evenNumbersArrAsString.length];
            for (int i = 0; i < evenNumbersArr.length; i++) {
                evenNumbersArr[i] = Integer.parseInt(evenNumbersArrAsString[evenNumbersArr.length - (i + 1)]);
            }
            System.out.println(Arrays.toString(evenNumbersArr));
        }
    }

    static void printACountOfTheLastOddNumbers(int[] arr, int count) {
        String allNumbersAsString = "";
        int counter = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] % 2 != 0 && counter < count) {
                allNumbersAsString += arr[i] + " ";
                counter++;
            }
        }
        if (allNumbersAsString.equals("")) {
            System.out.println("[]");
        } else {
            String[] oddNumbersArrAsString = allNumbersAsString.split(" ");
            int[] oddNumbersArr = new int[oddNumbersArrAsString.length];
            for (int i = 0; i < oddNumbersArr.length; i++) {
                oddNumbersArr[i] = Integer.parseInt(oddNumbersArrAsString[oddNumbersArr.length - (i + 1)]);
            }
            System.out.println(Arrays.toString(oddNumbersArr));
        }
    }
}
