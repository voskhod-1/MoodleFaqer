import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AutoMark {
    public static void AutoMarker(WebDriver driver) {
        try {
            String strJson = JsonIO.readStringFromFile("classes.json");
            Schedule schedule = JsonIO.stringAsSchedule(strJson);
            while (true) {
                boolean isMarked = false;
                System.out.println(NowDateTime.getTime());
                try {
                    String link = schedule.getNowClassLink((NowDateTime.isNumerator(false)) ? "numerator" : "denominator", NowDateTime.getDayOfWeek(), NowDateTime.getTime());
                    if (link != null) {
                        while (!isMarked) {
                            TimeUnit.MINUTES.sleep(5);
                            isMarked = Mark(driver, link);
                        }

                    }
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (com.google.gson.JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean Mark(WebDriver driver, String link) {
        if (!Objects.equals(driver.getCurrentUrl(), link)) {
            driver.get(link);
        }
        try {
            driver.findElement(By.id()).click();
            // Здесь будет логика
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

}
