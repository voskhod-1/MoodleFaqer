package Logic;

import com.google.gson.JsonSyntaxException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AutoMark {
    public static String autoMark(WebDriver driver, String strJson, Schedule schedule, boolean invert) {
        String outputStr;
        try {
            boolean isMarked = false;
            outputStr = NowDateTime.getWeekType(invert) + " " + NowDateTime.getDayOfWeek() + " " + NowDateTime.getTime() + "\n";
            Schedule.ClassInfo pairClass = schedule.getNowClassInfo(NowDateTime.getWeekType(invert), NowDateTime.getDayOfWeek(), NowDateTime.getTime());
            String link;
            if (pairClass != null) {
                link = schedule.getNowClassInfo(NowDateTime.getWeekType(invert), NowDateTime.getDayOfWeek(), NowDateTime.getTime()).getUrl();
            } else {
                link = null;
            }
            outputStr += link + "\n";
            int cnt = 1;
            if (link != null) {
                System.out.print(outputStr);
                isMarked = Mark(driver, link);
                while (!isMarked) {
                    System.out.printf("Не удалось отметить свое присутствие. Попытка %d\n", cnt);
                    cnt++;
                    isMarked = Mark(driver, link);
                    TimeUnit.MINUTES.sleep(3);
                }
            } else return outputStr;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void consoleMark(WebDriver driver, boolean invert) {
        try {
            String strJson = JsonIO.readStringFromFile("classes.json");
            Schedule schedule = Schedule.stringAsSchedule(strJson);
            while (true) {
                autoMark(driver, strJson, schedule, invert);
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
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
        } catch (NoSuchElementException | InterruptedException e) {
            return false;
        }
        return true;
    }

    public static boolean invertWeeks(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(NowDateTime.getWeekType(false));
        System.out.println("Если необходимо сменить тип недель, введите 'y");
        return scanner.nextLine().equals("y");
    }
}