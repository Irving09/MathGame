
/*
 * MathGame.java requires no other files.
 */
import javax.swing.*;

public class MathGame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private static final int nSquarePerRow = 20;
    private static final int nRow = 30;
    private static final int separate = 20;
    private static final int yseparate = 20;
    private static int squareW= 20;
    private static int squareH= 20;
    private static JFrame frame;

    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("MathGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //frame size
        frame.setSize(WIDTH , HEIGHT);

        frame.add(new GameBoard());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void drawSquare() {
        for(int r = 0; r < nRow; r++)
        {
            for(int c=0; c < nSquarePerRow; c++)
            {
                int x = separate*(r) + (squareW *r)+4;
                int y = yseparate + (squareH * c)+separate*c;


               // drawSingleBrick(x,y);

            }
        }
    }
/*
    public static void drawSingleBrick(int x, int y)
    {

        GameBoard brick =	new GameBoard();
        Random ran = new Random(System.currentTimeMillis());

        brick.setColor(ran.nextInt(5));
        frame.add(brick);
    }*/
}
