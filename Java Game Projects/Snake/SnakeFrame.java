import javax.swing.*;

public class SnakeFrame extends JFrame {

    SnakeFrame() {

        this.add(new SnakePanel());
        this.setTitle("SNAKE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); // center Frame

    }

}
