import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class NowDateTime {

    public static boolean isNumerator(boolean invert) {
        LocalDateTime today = LocalDateTime.now();
        int invertInt = 0;
        if (invert) invertInt = 1;
        WeekFields weekFields = WeekFields.of(Locale.ROOT);
        int weekNumber = today.get(weekFields.weekOfYear());
        return (weekNumber + invertInt) % 2 == 0;
    }

    public static String getDayOfWeek() {
        LocalDateTime today = LocalDateTime.now();
        return today.getDayOfWeek().toString();
    }

    public static String getTime(){
        LocalDateTime today = LocalDateTime.now();
        return today.toLocalTime().toString().substring(0, 5);
    }
}
