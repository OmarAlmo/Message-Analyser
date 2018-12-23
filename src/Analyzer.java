import java.io.*;
import java.util.Scanner;

public class Analyzer {
    public static void main(String[] Args){
        System.out.println("Enter file path (.txt): ");

        Scanner in = new Scanner(System.in);
        String fileName = in.next();

        System.out.println("Reading file...");
        DataReader.readFile(fileName);
        System.out.println("Finsihed reading file.");
    }

}
