package GUI;

import Logic.MoodleUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AMMenu extends JFrame {
    private JButton startBtn;
    private JButton cfgSchedBtn;
    private JPanel panel1;
    private JCheckBox invertChB;

    public AMMenu(MoodleUser user) {
        setContentPane(panel1);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AutoMark(user,invertChB.isSelected());
            }
        });
        cfgSchedBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ConfigEditor(user);
            }
        });
    }
}
