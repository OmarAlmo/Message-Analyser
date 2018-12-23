import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DataReader {

    private class Data {
        private String name, message;

        public Data(String name, String message) {
            this.name = name;
            this.message = message;
        }
    }

    private HashMap<Date, Data> hashMap;
    private ArrayList<String> users;


    public static void readFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            if (line == null) {
                System.out.println("ERROR: File is empty");
            }

            int counter  = 0;
            while (line != null) {



                System.out.println(line);
                line = reader.readLine();
                counter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading file.");
        }
    }
}
