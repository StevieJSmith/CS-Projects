import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int OBJECT_SIZE = 30; // size of objects
    static final int GAME_OBJECTS = ((SCREEN_WIDTH * SCREEN_HEIGHT) / OBJECT_SIZE); // amount of objects on screen at once
    static final int DELAY = 60; // game speed
    final int x[] = new int[GAME_OBJECTS]; // snake will not get bigger than game itself
    final int y[] = new int[GAME_OBJECTS];
    int snakeSize = 6; // snake body size
    int foodEaten = 0;
    int foodX; // x & y coordinates of food on screen
    int foodY;
    char direction = 'D'; // starting direction of snake (U = up, r = right, D = down, L = left)
    boolean isGameOver = true;
    Timer timer;
    Random random;

    SnakePanel() {

        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(0,0,0));
        this.setFocusable(true);
        this.addKeyListener(new adaptedKey()); // apply modified keys listener to panel

        startGame();

    }

    public void startGame() {
        generateFood();
        isGameOver = false;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        if(!isGameOver) {
            for (int i = 0; i < (SCREEN_HEIGHT / OBJECT_SIZE); i++) { // grid layout on screen
                g.drawLine(i * OBJECT_SIZE, 0, i * OBJECT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * OBJECT_SIZE, SCREEN_WIDTH, i * OBJECT_SIZE);
            }
            g.setColor(new Color(0, 240, 120));
            g.fillOval(foodX, foodY, OBJECT_SIZE, OBJECT_SIZE);

            for (int i = 0; i < snakeSize; i++) { // loop through snake's body
                if (snakeSize % 2 == 0) { // if snake size is even
                    if (i % 2 == 0) { // if snake segment is even
                        g.setColor(new Color(240, 120, 0));
                        g.fillRect(x[i], y[i], OBJECT_SIZE, OBJECT_SIZE);
                    } else { // if segment is odd
                        g.setColor(new Color(0, 120, 240));
                        g.fillRect(x[i], y[i], OBJECT_SIZE, OBJECT_SIZE);
                    }
                }else{ // if snake size is odd
                    if (i % 2 == 0) {
                        g.setColor(new Color(240, 0, 120));
                        g.fillRect(x[i], y[i], OBJECT_SIZE, OBJECT_SIZE);
                    } else {
                        g.setColor(new Color(240, 240, 60));
                        g.fillRect(x[i], y[i], OBJECT_SIZE, OBJECT_SIZE);
                    }
                }
            }
            g.setColor(new Color(0, 240, 120)); // display Player score at top left corner
            g.setFont(new Font("Arial", Font.BOLD, 25));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + foodEaten, 0, (g.getFont().getSize()));
        }else{
            gameOver(g);
        }
    }

    public void generateFood() { // randomly place food on the grid
        foodX = random.nextInt((SCREEN_WIDTH/OBJECT_SIZE))*OBJECT_SIZE;
        foodY = random.nextInt((SCREEN_HEIGHT/OBJECT_SIZE))*OBJECT_SIZE;

    }

    public void snakeMove() {
        for(int i = snakeSize; i > 0; i--) { // head to end
            x[i] = x[i-1]; // shift over by 1
            y[i] = y[i-1];
        }

        switch(direction) { // update position
            case 'U' -> y[0] = y[0] - OBJECT_SIZE;
            case 'D' -> y[0] = y[0] + OBJECT_SIZE;
            case 'L' -> x[0] = x[0] - OBJECT_SIZE;
            case 'R' -> x[0] = x[0] + OBJECT_SIZE;
            default -> y[0] = y[0];
        }

    }

    public void checkFood() {
        if(x[0] == foodX && y[0] == foodY) { // if snake head is in the location of the food
            snakeSize++; // increase size of snake
            foodEaten++; // player Score
            generateFood(); // make food move location on screen
        }
    }

    public void checkCollisions() {

        for(int i = snakeSize; i > 0; i--) { // head and body have same position check
            if(x[0] == x[i] && y[0] == y[i]) {
                isGameOver = true;
            }
        }
        if(x[0] < 0 || x[0] > SCREEN_WIDTH || y[0] < 0 || y[0] > SCREEN_HEIGHT) { // snake out of bounds (head out of frame)
            isGameOver = true;
        }

    }

    public void gameOver(Graphics g) {

        g.setColor(new Color(240, 50, 50)); // display Game Over when 'gameOver' is triggered
        g.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over !!!", ((SCREEN_WIDTH - metrics.stringWidth("Game Over !!!")) / 2), (SCREEN_HEIGHT / 2));

        g.setColor(new Color(0, 240, 120)); // enhance the player's final score on Game Over screen and center the position of score
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Score: " + foodEaten, ((SCREEN_WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2), (g.getFont().getSize()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(!isGameOver) {
            snakeMove();
            checkFood();
            checkCollisions();
        }
        repaint();

    }

    public class adaptedKey extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {

            switch (event.getKeyCode()) {
                case KeyEvent.VK_UP -> {
                    if(direction != 'D') {
                        direction = 'U';
                    }
                }
                case KeyEvent.VK_DOWN -> {
                    if(direction != 'U') {
                        direction = 'D';
                    }
                }
                case KeyEvent.VK_LEFT -> {
                    if(direction != 'R') {
                        direction = 'L';
                    }
                }
                case KeyEvent.VK_RIGHT -> {
                    if(direction != 'L') {
                        direction = 'R';
                    }
                }
                default -> direction = 'D';
            }
        }
    }
}
