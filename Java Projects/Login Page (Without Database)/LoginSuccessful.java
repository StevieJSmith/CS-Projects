import javax.swing.*;
import java.awt.*;

public class LoginSuccessful {

    private JFrame loginSuccessfulFrame = new JFrame();
    private JLabel welcomeLabel = new JLabel();

    LoginSuccessful(String id) {


        welcomeLabel.setBounds(200, 150, 500, 25);
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 25));
        welcomeLabel.setText("Welcome to your account " + id + " !!!");


        loginSuccessfulFrame.add(welcomeLabel);

        loginSuccessfulFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginSuccessfulFrame.setSize(800, 400);
        loginSuccessfulFrame.setLocationRelativeTo(null);
        loginSuccessfulFrame.setLayout(null);
        loginSuccessfulFrame.setVisible(true);

    }

}
