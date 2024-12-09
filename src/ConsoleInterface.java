import org.openqa.selenium.WebDriver;

import java.util.Scanner;

public class ConsoleInterface {

    public static void clearConsole() {
        System.out.print("\033[H\033[J");
    }

    public static void getMenu(WebDriver driver) {
        Scanner sc = new Scanner(System.in);
        clearConsole();
        System.out.println("Меню скрипта: \n1. Автоматическая посещаемость\n2. Нафармить клонов в лекцию\n3. Положить сайт\n4. Разлогиниться");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                AutoMark.AutoMarker(driver);
                break;
            case 2:
                UserCloner.getMenu(driver);
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("Ошибка");
        }
    }

    public static void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("MoodleFaqer v0.1\nДля продолжения работы авторизуйтесь в системе Moodle\nЛогин: ");
        String login = sc.nextLine();
        System.out.print("Введите пароль: ");
        String password = sc.nextLine();
        try {
            MoodleUser student = new MoodleUser(login, password);
            System.out.println(student.getUserName());
            getMenu(student.getDriver());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            sc.close();
        }
    }
}
