public class Main {

    public static void main(String[] args) {
        String s = "The quick BrownFox jumps over the lazy Dog";
        StringBuilder currentString = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (Character.isUpperCase(currentChar)){
                currentString.append(currentChar);
            } else if (currentString.length() > 0 && Character.isLetter(currentChar)){
                currentString.append(currentChar);
            } else if (currentString.length() > 0){
                System.out.println(currentString.toString());
                currentString = new StringBuilder();
            }
        }
        if (currentString.length() > 0){
            System.out.println(currentString);
        }
    }
}
