package GUI;

import Logic.JsonIO;
import Logic.MoodleUser;
import Logic.Schedule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AMMenu extends JFrame {
    private JButton startBtn;
    private JButton cfgSchedBtn;
    private JPanel panel1;

    public AMMenu(MoodleUser user) {
        setContentPane(panel1);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AutoMark(user);
                dispose();
            }
        });
    }

}
