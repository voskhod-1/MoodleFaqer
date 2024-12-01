import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class AutoMark {
    static String json = """
            {
              "classes": {
                "numerator": {
                  "MONDAY": {
                    "11:30": "https://edu.vsu.ru/mod/attendance/view.php?id=1308317",
                    "13:25": "https://edu.vsu.ru/mod/attendance/view.php?id=1308363",
                    "15:10": "https://edu.vsu.ru/mod/attendance/view.php?id=1262292"
                  },
                  "FRIDAY": {
                    "8:00": "https://edu.vsu.ru/mod/attendance/view.php?id=1307988"
                  }
                },
                "denominator": {
                  "MONDAY": {
                    "11:30": "https://edu.vsu.ru/mod/attendance/view.php?id=1308317",
                    "13:25": "https://edu.vsu.ru/mod/attendance/view.php?id=1308363",
                    "15:10": "https://edu.vsu.ru/mod/attendance/view.php?id=1262292"
                  },
                  "FRIDAY": {
                    "8:00": "https://edu.vsu.ru/mod/attendance/view.php?id=1307988"
                  }
                }
              }
            }
            """;


    public static void Mark(WebDriver driver) {
        try {
            Schedule schedule = JsonIO.stringAsSchedule(json);

            while (true) {
                try {
                    String link = schedule.getNowClassLink((NowDateTime.isNumerator(false)) ? "numerator" : "denominator", NowDateTime.getDayOfWeek(), NowDateTime.getTime());
                    if (link != null) {
                        driver.get(link);
                    }
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (com.google.gson.JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

}
