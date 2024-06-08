import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        //Creates a frame and gives a title
        JFrame fr = new JFrame();
        fr.setTitle("Brick Breaker Innovative Assignment");
        fr.setSize(700, 600);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.setVisible(true);
        fr.setResizable(false);

        Play play = new Play();
        fr.add(play);
    }
}
