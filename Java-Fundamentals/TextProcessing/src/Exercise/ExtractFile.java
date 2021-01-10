package Exercise;

import java.util.Scanner;

public class ExtractFile {
    public static void main(String[] args){
        String path = new Scanner(System.in).nextLine();
        String file = path.substring(path.lastIndexOf("\\"));

        String fileName = file.substring(1, file.indexOf("."));
        String fileExtension = file.substring(file.indexOf(".") + 1);
        System.out.println("File name: " + fileName);
        System.out.println("File extension: " + fileExtension);
    }
}
