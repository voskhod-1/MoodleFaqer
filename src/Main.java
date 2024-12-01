import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            if (args[0].startsWith("--GUI")) System.out.println("GUI пока не готов");
        }else{
            ConsoleInterface.start();
        }
    }
}
