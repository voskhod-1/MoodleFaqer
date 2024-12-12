package Logic;

import com.google.gson.Gson;

import java.io.*;
import java.util.stream.Collectors;

public class JsonIO {

    public static Schedule stringAsSchedule(String data) throws com.google.gson.JsonSyntaxException {
        Gson gson = new Gson();
        Schedule schedule = gson.fromJson(data, Schedule.class);
        return schedule;
    }

    public static String readStringFromFile(String fileName) throws IOException{
        String inStr;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            inStr = reader.lines().map(String::trim).collect(Collectors.joining());
        }
        return inStr;
    }

    public static void writeStringToFile(String fileName, String str) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(str);
        }
    }

}
