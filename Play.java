import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Play extends JPanel implements ActionListener, KeyListener {
    private int no_of_brick = 21;
    private Timer t;
    private int delay = 5;  
    private int ballX = 320;
    private int ballY = 450;
    private int moveX = -1;
    private int moveY = -2;
    private int PX = 350;
    private boolean play = false;
    private Bricks_Generator brc;

    public Play() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        t = new Timer(delay, this);
        t.start();
        brc = new Bricks_Generator(3, 7);
    }

    public void paint(Graphics g) {
        //Background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        //borders
        g.setColor(Color.green);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(0, 3, 3, 592);
        g.fillRect(691, 3, 3, 592);

        //paddle
        g.setColor(Color.blue);
        g.fillRect(PX, 550, 100, 12);

        //Ball
        g.setColor(Color.yellow);
        g.fillOval(ballX, ballY, 20, 20);

        //bricks
        brc.draw((Graphics2D) g);

        //Game Over
        if (ballY >= 570) {
            play = false;
            moveX = 0;
            moveY = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over!!", 250, 300);
        }

        //Winning Condition
        if (no_of_brick == 0) {
            play = false;
            moveX = 0;
            moveY = 0;
            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You WON!!", 250, 300);
        }
    }

    public void left_move() {
        play = true;
        PX -= 20;
    }

    private void right_move() {
        play = true;
        PX += 20;
    }

    //Key Listener
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (PX <= 0)
                PX = 0;
            else
                left_move();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (PX >= 600)
                PX = 600;
            else
                right_move();
        }
        repaint();
    }

    //Action Listener
    @Override
    public void actionPerformed(ActionEvent e) {

        if (play) {

            //Condition for the leftmost side or the rightmost side
            if (ballX <= 0 || ballX >= 670)
                moveX = -moveX;

            //Condition for the uppermost side
            if (ballY <= 0)
                moveY = -moveY;

            //making rectangle around paddle and ball
            Rectangle paddle = new Rectangle(PX, 550, 100, 8);
            Rectangle ball = new Rectangle(ballX, ballY, 20, 20);


            //Checking if the rectangle of ball and paddle are intersecting or not
            if (ball.intersects(paddle))
                moveY = -moveY;

            //Making rectangles  around all the bricks
            for (int i = 0; i < brc.bricks.length; i++) {
                for (int j = 0; j < brc.bricks[0].length; j++) {
                    if (brc.bricks[i][j] > 0) {
                        int width = brc.bWidth;
                        int height = brc.bHeight;
                        int bXpos = 80 + (j * width);
                        int bYpos = 50 + (i * height);

                        Rectangle bricks = new Rectangle(bXpos, bYpos, width, height);

                        if (ball.intersects(bricks)) {
                            brc.setBrick(0, i, j);
                            no_of_brick--;

                            if (ballX + 19 <= bXpos || ballX + 1 >= bXpos + width)
                                moveX = -moveX;
                            else
                                moveY = -moveY;
                        }
                    }
                }
            }

            //Move the ball by the values given
            ballX += moveX;
            ballY += moveY;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
