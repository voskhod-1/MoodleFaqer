import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;


public class MoodleUser {

    String login,password;
    WebDriver driver = new ChromeDriver();
    public MoodleUser(String login, String password) throws IOException {
        this.login = login;
        this.password = password;
        if (!logIn()) {
            driver.quit();
            throw new IOException("Ошибка авторизации");
        }
    }
    private boolean logIn()  {
        driver.get("https://edu.vsu.ru/login/index.php");
        driver.findElement(By.id("username")).sendKeys(login);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginbtn")).click();
        List<WebElement> errChk = driver.findElements(By.className("alert-danger"));
        return errChk.isEmpty();
    }
    public String getUserName(){
        driver.get("https://edu.vsu.ru/my/");
        String userName = driver.findElement(By.className("usertext")).getText();
        return userName;
    }

    public WebDriver getDriver(){
        return driver;
    }
}
