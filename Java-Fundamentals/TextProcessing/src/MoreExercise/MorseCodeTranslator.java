package MoreExercise;

import java.util.Scanner;

public class MorseCodeTranslator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] morseCode = scanner.nextLine().split("\\s+");
        StringBuilder decryptMessage = new StringBuilder();
        for (int i = 0; i < morseCode.length; i++) {
            char currentChar;
            switch (morseCode[i]) {
                case ".-":
                    currentChar = 'A';
                    break;
                case "-...":
                    currentChar = 'B';
                    break;
                case "-.-.":
                    currentChar = 'C';
                    break;
                case "-..":
                    currentChar = 'D';
                    break;
                case ".":
                    currentChar = 'E';
                    break;
                case "..-.":
                    currentChar = 'F';
                    break;
                case "--.":
                    currentChar = 'G';
                    break;
                case "....":
                    currentChar = 'H';
                    break;
                case "..":
                    currentChar = 'I';
                    break;
                case ".---":
                    currentChar = 'J';
                    break;
                case "-.-":
                    currentChar = 'K';
                    break;
                case ".-..":
                    currentChar = 'L';
                    break;
                case "--":
                    currentChar = 'M';
                    break;
                case "-.":
                    currentChar = 'N';
                    break;
                case "---":
                    currentChar = 'O';
                    break;
                case ".--.":
                    currentChar = 'P';
                    break;
                case "--.-":
                    currentChar = 'Q';
                    break;
                case ".-.":
                    currentChar = 'R';
                    break;
                case "...":
                    currentChar = 'S';
                    break;
                case "-":
                    currentChar = 'T';
                    break;
                case "..-":
                    currentChar = 'U';
                    break;
                case "...-":
                    currentChar = 'V';
                    break;
                case ".--":
                    currentChar = 'W';
                    break;
                case "-..-":
                    currentChar = 'X';
                    break;
                case "-.--":
                    currentChar = 'Y';
                    break;
                case "--..":
                    currentChar = 'Z';
                    break;
                case "-----":
                    currentChar = '0';
                    break;
                case ".----":
                    currentChar = '1';
                    break;
                case "..---":
                    currentChar = '2';
                    break;
                case "...--":
                    currentChar = '3';
                    break;
                case "....-":
                    currentChar = '4';
                    break;
                case ".....":
                    currentChar = '5';
                    break;
                case "-....":
                    currentChar = '6';
                    break;
                case "--...":
                    currentChar = '7';
                    break;
                case "---..":
                    currentChar = '8';
                    break;
                case "----.":
                    currentChar = '9';
                    break;
                case "|":
                    currentChar = ' ';
                    break;
                default:
                    currentChar = '.';
                    break;
            }
            decryptMessage.append(currentChar);
        }
        System.out.println(decryptMessage);
    }
}
