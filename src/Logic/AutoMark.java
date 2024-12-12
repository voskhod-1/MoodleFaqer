package Logic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AutoMark {
    public static String AutoMarker(WebDriver driver) {
        String outputStr;
        try {
            String strJson = JsonIO.readStringFromFile("classes.json");
            Schedule schedule = JsonIO.stringAsSchedule(strJson);
            while (true) {
                boolean isMarked = false;
                try {
                    outputStr = NowDateTime.getWeekType(false) + " " + NowDateTime.getDayOfWeek() + " " + NowDateTime.getTime() + "\n";
                    String link = schedule.getNowClassLink(NowDateTime.getWeekType(false), NowDateTime.getDayOfWeek(), NowDateTime.getTime());
                    outputStr += link + "\n";
                    int cnt = 1;
                    if (link != null) {
                        //System.out.println(link);
                        System.out.print(outputStr);
                        isMarked = Mark(driver, link);
                        while (!isMarked) {
                            System.out.printf("Не удалось отметить свое присутствие. Попытка %d\n", cnt);
                            cnt++;
                            isMarked = Mark(driver, link);
                            TimeUnit.MINUTES.sleep(3);
                        }
                    } else return outputStr;
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
        return null;
    }

    protected static boolean Mark(WebDriver driver, String link) { //Должен быть private
        if (!Objects.equals(driver.getCurrentUrl(), link)) {
            driver.get(link);
        }
        try {
            driver.findElement(By.linkText("Отметить свое присутствие")).click();
            TimeUnit.SECONDS.sleep(3);
            driver.findElement(By.name("status")).click();
            TimeUnit.SECONDS.sleep(3);
            driver.findElement(By.id("id_submitbutton")).click();
            // Здесь будет логика
        } catch (NoSuchElementException e) {
            return false;
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }
}
