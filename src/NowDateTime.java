import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class NowDateTime {
    static LocalDateTime today = LocalDateTime.now();

    public static boolean isNumerator(boolean invert) {
        int invertInt = 0;
        if (invert) invertInt = 1;
        WeekFields weekFields = WeekFields.of(Locale.ROOT);
        int weekNumber = today.get(weekFields.weekOfYear());
        return (weekNumber + invertInt) % 2 == 0;
    }

    public static String getDayOfWeek() {
        return today.getDayOfWeek().toString();
    }

    public static String getTime(){
        return today.toLocalTime().toString().substring(0, 5);
    }
}
