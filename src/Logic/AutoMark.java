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
    public static String autoMark(WebDriver driver, Schedule schedule, boolean invert) {
        String outputStr;
        boolean isMarked = false;
        outputStr = NowDateTime.getWeekType(invert) + " " + NowDateTime.getDayOfWeek() + " " + NowDateTime.getTime() + "\n";
        Schedule.ClassInfo pairClass = schedule.getNowClassInfo(NowDateTime.getWeekType(invert), NowDateTime.getDayOfWeek(), NowDateTime.getTime());
        String link;
        if (pairClass == null) {
            link = null;

        } else {
            link = pairClass.getUrl();
            if (link != null) {
                outputStr += link + "\n";
                System.out.println(outputStr);
                if (pairClass.getType().equals("Lecture")) {

                    isMarked = mark(driver, link);
                    while (!isMarked) {
                        return link;
                    }
                    return outputStr;
                } else if (pairClass.getType().equals("Practical"))
                    isMarked = joinToPractical(driver, link);
                while (!isMarked) {
                    return link;
                }
                return outputStr;
            } else if (pairClass.getType().equals("Seminar")) {
                isMarked = joinToSeminar(driver, link);
                while (!isMarked) {
                    return link;
                }
            }
        }
        return null;
    }

    public static void consoleMark(WebDriver driver, boolean invert) {
        try {
            String strJson = JsonIO.readStringFromFile("classes.json");
            Schedule schedule = Schedule.stringAsSchedule(strJson);
            int cnt = 0;
            while (true) {
                if (autoMark(driver, schedule, invert).contains("mod")) {
                    cnt++;
                    System.out.println("Не удалось отметиться. Попытка " + cnt);
                } else {
                    cnt = 0;
                    System.out.println("Успешно отметил вас");
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean joinToPractical(WebDriver driver, String link) {

        return false;
    }

    public static boolean joinToSeminar(WebDriver driver, String link) {
        return false;
    }

    public static boolean mark(WebDriver driver, String link) { //Должен быть private
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

    public static boolean invertWeeks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(NowDateTime.getWeekType(false));
        System.out.println("Если необходимо сменить тип недель, введите 'y");
        return scanner.nextLine().equals("y");
    }
}