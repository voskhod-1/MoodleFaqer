package GUI;

import Logic.JsonIO;
import Logic.MoodleUser;
import Logic.NowDateTime;
import Logic.Schedule;
import org.openqa.selenium.WebDriver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

class AutoMarkGui extends Logic.AutoMark {
    public static String AutoMark(WebDriver driver, String strJson, Schedule schedule) {
        String outputStr;
        try {
            boolean isMarked = false;
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

public class AutoMark extends JFrame {
    private JButton closeBtn;
    private JTextArea textArea1;
    private JPanel panel1;

    public AutoMark(MoodleUser user) {
        super("AutoMark");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setSize(500, 500);
        setVisible(true);
        try {
            String strJson = JsonIO.readStringFromFile("classes.json");
            Schedule schedule = JsonIO.stringAsSchedule(strJson);
            while (true) {
                closeBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new Menu(user);
                    }
                });
                String outputInfo = AutoMarkGui.AutoMark(user.getDriver(), strJson, schedule);
                textArea1.setText(outputInfo != null ? outputInfo : "\n");
                TimeUnit.MINUTES.sleep(1);
            }
        } catch (Exception e) {
            new Error(e.toString());
            dispose();
        }
    }
}
