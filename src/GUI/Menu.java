package GUI;

import Logic.MoodleUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JPanel MenuPanel;
    private JLabel Hello;
    private JButton autoMarkBtn;
    private JButton trollBtn;
    private JButton button3;

    public Menu(MoodleUser user) {
        super("Menu");
        setContentPane(MenuPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Hello.setText("Здраствуйте, " + user.getUserName());
        autoMarkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AMMenu(user);
                dispose();
            }
        });
        trollBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trollBtn.setName("Не работает");
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button3.setName("Не работает");
            }
        });
    }
}


