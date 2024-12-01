import com.google.gson.Gson;

import java.io.IOException;

public class JsonIO {

    public static Schedule stringAsSchedule(String data) throws com.google.gson.JsonSyntaxException {
        Gson gson = new Gson();
        Schedule schedule = gson.fromJson(data, Schedule.class);
        return schedule;
    }


}
