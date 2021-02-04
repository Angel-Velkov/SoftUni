package Lab.SmartArray;

public class Main {
    public static void main(String[] args) {
        SmartArray smartArray = new SmartArray();

        smartArray.add(1);
        for (int i = 1; i < 100; i++) {
            smartArray.add(0, i + 1);
        }
    }
}
