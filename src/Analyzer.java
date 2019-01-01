import java.io.*;
import java.util.Scanner;

public class Analyzer {
    public static void main(String[] Args){
        DataReader dataReader = new DataReader();
        System.out.println("Enter file path (.txt): ");

        Scanner in = new Scanner(System.in);
        String fileName = in.next();

        System.out.println("Reading file...");
        dataReader.readFile("/Users/omaralmo/Documents/Projects/chat_demo_copy.txt");
        System.out.println("Finsihed reading file.");
    }

}
