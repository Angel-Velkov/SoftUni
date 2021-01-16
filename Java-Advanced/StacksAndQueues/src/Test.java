public class Test {
    public static void main(String[] args) {
        int num = 28815;

        int hours = num / 3600;
        num -= hours * 3600;
        int mins = num / 60;
        num -= mins * 60;
        int sec = num;
        System.out.println(hours);
        System.out.println(mins);
        System.out.println(sec);
    }
}
