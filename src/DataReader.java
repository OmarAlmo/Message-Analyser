import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private final Pattern messagePattern = Pattern.compile("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]), ([01]?[0-9]|2[0-3]):[0-5][0-9]) (-) (.*)(:) (.*)");
                    //group 1 = date; group 6 = user; group 8 = message
    private final Pattern singleMessagePattern = Pattern.compile(".*");
    public DataReader(){
        this.users = new ArrayList<>(30);
        hashMap = new HashMap<>(7919);
    }


    public void readFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader;
        Matcher matcher;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            if (line == null) {
                System.out.println("Error: File is EMPTY");
            }

            int counter = 0;
            while (line != null) {
                matcher = messagePattern.matcher(line);
                if (matcher.matches()) {
                    date = matcher.group(1);
                    user = matcher.group(6);
                    message = matcher.group(8);

                    Data data = new Data(date, message);
                    hashMap.put(user, data);

                    if (verifyUser(user) == -1)
                        users.add(user);
                } else { //line is a single message from the previous user
                    matcher = singleMessagePattern.matcher(line);
                    if (matcher.matches()){
                        message = matcher.group(0);
                        Data data = new Data(date, message);
                        hashMap.put(user, data);
                    }
                }

                line = reader.readLine();
                counter++;
            }

            //System.out.println(users);
            System.out.println(hashMap.toString());

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
