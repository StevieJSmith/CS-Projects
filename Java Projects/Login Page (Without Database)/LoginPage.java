import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage implements ActionListener {

    private JFrame loginFrame = new JFrame();
    private JButton loginButton = new JButton("Login");
    private JButton resetButton = new JButton("Reset");
    private JTextField idField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JLabel idLabel = new JLabel("User ID:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JLabel messageLabel = new JLabel("");
    private HashMap<String, String> loginInfo = new HashMap<String, String>(); // globally available

    public LoginPage(HashMap<String, String> loginInfo) {
        this.loginInfo = loginInfo;

        idLabel.setBounds(250, 100, 75, 25);
        idLabel.setFont(new Font("sans-serif", Font.PLAIN, 15));
        idLabel.setForeground(new Color(255, 255, 255));
        passwordLabel.setBounds(250, 150, 75, 25);
        passwordLabel.setFont(new Font("sans-serif", Font.PLAIN, 15));
        passwordLabel.setForeground(new Color(255, 255, 255));

        messageLabel.setBounds(300, 250, 250, 35);
        messageLabel.setFont(new Font("sans-serif", Font.ITALIC, 20));

        idField.setBounds(325, 100, 200, 25);
        passwordField.setBounds(325, 150, 200, 25);

        loginButton.setBounds(310, 200, 100, 25);
        loginButton.setBackground(new Color(0, 0, 0));
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.addActionListener(this);
        resetButton.setBounds(440, 200, 100, 25);
        resetButton.setBackground(new Color(0, 0, 0));
        resetButton.setForeground(new Color(255, 255, 255));
        resetButton.addActionListener(this);


        loginFrame.add(idLabel);
        loginFrame.add(passwordLabel);
        loginFrame.add(messageLabel);
        loginFrame.add(idField);
        loginFrame.add(passwordField);
        loginFrame.add(loginButton);
        loginFrame.add(resetButton);

        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(800, 400);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.getContentPane().setBackground(new Color(0, 0, 0));
        loginFrame.setLayout(null);
        loginFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resetButton) { // if user presses the reset button
            idField.setText(""); // clear fields when the reset button is clicked!
            passwordField.setText("");
        } else if (e.getSource() == loginButton) { // if user presses the login button
            String id = idField.getText();
            String password = String.valueOf(passwordField.getPassword()); // using a passwordField access method!

            if (loginInfo.containsKey(id)) { // check if user id is valid
                if (loginInfo.get(id).equals(password)) { // check if user id is stored with the inputted password
                    messageLabel.setForeground(new Color(0, 240, 120));
                    messageLabel.setText("Successfully Logged In!!!");

                    loginFrame.dispose();
                    LoginSuccessful loginSuccessful = new LoginSuccessful(id);

                } else { // if given invalid password
                    messageLabel.setForeground(new Color(255, 50, 50));
                    messageLabel.setText("Wrong Password!!!");
                }
            } else { // if key was not found (username not within HashMap)
                messageLabel.setForeground(new Color(240, 120, 0));
                messageLabel.setText("Username was not found!!!");
            }
        }
    }
}