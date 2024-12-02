import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AutoMark {
    public static void Mark(WebDriver driver) {
        try {
            String strJson = JsonIO.readStringFromFile("classes.json");
            Schedule schedule = JsonIO.stringAsSchedule(strJson);
            while (true) {
                System.out.println(NowDateTime.getTime());
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
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
