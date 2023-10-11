import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener {

    private JFrame gameFrame = new JFrame(); // create swing elements
    private JPanel titlePanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel textField = new JLabel();
    private JButton[] buttons = new JButton[9]; // 9 buttons for each possible position
    private boolean isTurnO; // true = O, False = X
    private boolean isGameOver = false; // check if game is over



    public TicTacToe() {
        // JFrame specifications
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(750,750);
        gameFrame.getContentPane().setBackground(new Color(100,100,100));
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setVisible(true);

        // JLabel specifications
        textField.setBackground(new Color(50,50,50));
        textField.setForeground(new Color(0, 240, 120));
        textField.setFont(new Font("sans-serif", Font.BOLD, 60));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("TIC-TAC-TOE");
        textField.setOpaque(true);

        // JPanel specifications
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,750,100);

        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(new Color(255,255,255));

        // JButton Specifications
        for(int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("sans-serif", Font.BOLD, 120));
            buttons[i].setBackground(new Color(50,50,50));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        titlePanel.add(textField); // place text on 'titlePanel'
        gameFrame.add(titlePanel, BorderLayout.NORTH); // assign 'titlePanel' to the north of the Frame!
        gameFrame.add(buttonPanel); // place buttons on the Frame

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 0; i < 9 && !isGameOver; i++){
                if (e.getSource() == buttons[i]) { // if event was triggered on a button
                    if (isTurnO) {
                        if (buttons[i].getText() == "") {
                            buttons[i].setForeground(new Color(0, 120, 240)); // O's colour is blue!
                            buttons[i].setText("O");
                            if (!isGameOver) {
                                isTurnO = false;
                                textField.setText("X's Turn!");
                            }
                            check("O"); // check if player wins after making a move
                        }
                    } else {
                        buttons[i].setForeground(new Color(240, 120, 0)); // X's colour is Orange!
                        buttons[i].setText("X");
                        if (!isGameOver) {
                            isTurnO = true;
                            textField.setText("O's Turn!");
                        }
                        check("X");
                    }
                }
        }
    }

    public void firstTurn() {

        try {
            Thread.sleep(5000); // try to display title for 5 seconds before deciding on the player to go first
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double randomChoice = Math.round(Math.random() * 10); // number between 1-10
        isTurnO = ( randomChoice <= 5) ? true : false; // if 1-5 starting player is 'O', if 6-10 starting player is 'X'
        String text = (isTurnO) ? "O's Turn!" : "X's Turn!";
        textField.setText(text);
    }

    public void check(String player) { // check if there is a winner from the win conditions
        boolean aWinner = false;
        String how = "";
        if((buttons[0].getText() == player && buttons[1].getText() == player && buttons[2].getText() == player)
                || (buttons[3].getText() == player && buttons[4].getText() == player && buttons[5].getText() == player)
        || (buttons[6].getText() == player && buttons[7].getText() == player && buttons[8].getText() == player)){
            aWinner = true; // win by row
            how = "Row";
        }
        else if((buttons[0].getText() == player && buttons[3].getText() == player && buttons[6].getText() == player)
                || (buttons[1].getText() == player && buttons[4].getText() == player && buttons[7].getText() == player)
                || (buttons[2].getText() == player && buttons[5].getText() == player && buttons[8].getText() == player)) {
            aWinner = true; // win by column
            how = "Column";
        }

        else if((buttons[0].getText() == player && buttons[4].getText() == player && buttons[8].getText() == player)
                || (buttons[2].getText() == player && buttons[4].getText() == player && buttons[6].getText() == player)){
            aWinner = true; // win by diagonal
            how = "Diagonal";
        }

        if (aWinner && player == "O") { // if there is a winner parse the player and how they won to 'winner'
            winner(player, how);
        }else if(aWinner && player == "X"){
            winner(player, how);
        }
    }

    public void winner(String player, String winBy) { // display the winner and how they won!
        textField.setText(player + " wins by 3 in a " + winBy);
        textField.setEnabled(false);
        this.isGameOver = true; // game is over
        for(int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false); // disable button interactivity after winner is displayed
        }
    }
}
