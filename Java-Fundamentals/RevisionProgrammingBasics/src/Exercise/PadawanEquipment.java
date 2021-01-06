package Exercise;

import java.util.Scanner;

public class PadawanEquipment {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double money = Double.parseDouble(scan.nextLine());
        int studentsCount = Integer.parseInt(scan.nextLine());
        double lightsabersPrice = Double.parseDouble(scan.nextLine());
        double robePrice = Double.parseDouble(scan.nextLine());
        double beltPrice = Double.parseDouble(scan.nextLine());

        double moreSabers = Math.ceil(studentsCount * 1.1);
        double freeBelt = studentsCount - Math.floor(studentsCount * 1.0 / 6);

        double totalPrice = (freeBelt * beltPrice) + (moreSabers * lightsabersPrice) + (studentsCount * robePrice);

        if (money >= totalPrice) {
            System.out.printf("The money is enough - it would cost %.2flv.", totalPrice);
        } else {
            System.out.printf("Ivan Cho will need %.2flv more.", Math.abs(totalPrice - money));
        }
    }
}
