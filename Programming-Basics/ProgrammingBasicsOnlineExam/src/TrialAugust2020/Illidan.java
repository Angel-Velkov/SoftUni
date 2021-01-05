package TrialAugust2020;

import java.util.Scanner;

public class Illidan {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int players = Integer.parseInt(scan.nextLine());
        int power = Integer.parseInt(scan.nextLine());
        int health = Integer.parseInt(scan.nextLine());
        int attack = players * power;
        int diff = Math.abs(attack - health);

        if (attack >= health) {
            System.out.printf("Illidan has been slain! You defeated him with %d points.", diff);
        } else {
            System.out.printf("You are not prepared! You need %d more points.", diff);
        }
    }
}
