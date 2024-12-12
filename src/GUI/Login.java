package GUI;

import Logic.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Login extends JFrame{
    private JButton loginBtn;
    private JTextField loginField;
    private JPasswordField pwdField;
    private JPanel Main;

    private Login(){
        super("Логин");
        setContentPane(Main);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450,300);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login attempt");
                //System.out.println(pwdField.getText());
                try {
                    MoodleUser student = new MoodleUser(loginField.getText(), pwdField.getText());
                    new Menu(student);
                    dispose();
                }catch (IOException ex){
                    new Error(ex.toString());
                    dispose();
                }
            }
        });

    }

    public static void main(String[] args) {
        new Login();
    }
}
