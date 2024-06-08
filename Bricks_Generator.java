import java.awt.*;

public class Bricks_Generator {
    public int[][] bricks;
    public int bWidth;
    public int bHeight;

    public Bricks_Generator(int row, int column) {
        bricks = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                bricks[i][j] = 1;
            }
        }

        bWidth = 540 / column;
        bHeight = 150 / row;
    }

    public void setBrick(int value, int r, int c) {
        bricks[r][c] = value;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (bricks[i][j] > 0) {

                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(j * bWidth + 80, i * bHeight + 50, bWidth, bHeight);

                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(2));
                    g.drawRect(j * bWidth + 80, i * bHeight + 50, bWidth, bHeight);
                }
            }
        }


    }

}
