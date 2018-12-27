import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static java.lang.reflect.Array.getLength;

public class DataReader {

    private class Data {
        private String message;
        private String date;

        public Data(String date, String message) {
            this.date = date;
            this.message = message;
        }
    }

    private HashMap<String, Data> hashMap;
    private ArrayList<String> users;
    private String date, user, message;

    public DataReader(){
        this.users = new ArrayList<>(30);
        hashMap = new HashMap<>(100);
    }


    public void readFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            System.out.println("line1: "+ line);
            line = reader.readLine();
            System.out.println("line 2: " + line);

            if (line == null) {
                System.out.println("Error: File is EMPTY");
            }

            while (line != null) {
                String[] part1 = line.split(" - "); // part1[0] = date, part1[1] = name : date

                if (getLength(part1)<1){ // the message is on single line that is for the latest user above
                    message = part1[0];
                    Data singleMessage = new Data(date, message);
                    hashMap.put(user, singleMessage);
                    line = reader.readLine();
                }

                String[] part2 = part1[1].split(": ");// part2[0] = name, part2[1] = message

                date = part1[0];
                user = part2[0];
                message = part2[1];

                System.out.println(hashMap);
                System.out.println(users);

                Data data = new Data (date, message);
                if (verifyUser(user) == -1)
                    users.add(user);
                hashMap.put(user, data);


                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading file.");
        }
    }

    public int verifyUser(String name){
        for (int i = 0; i < users.size(); i++){
            if (users.contains(name)){
                return users.indexOf(name);
            }
        }
        return -1;
    }
}
