
/*
 * MathGame.java requires no other files.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class MathGame {

    private static final int WIDTH = 420;
    private static final int HEIGHT = 600;
    private static final int nSquarePerRow = 20;
    private static final int nRow = 30;
    private static final int separate = 20;
    private static final int yseparate = 20;
    private static int squareW= 20;
    private static int squareH= 20;
    private static JFrame frame;
    private GameBoard gb;
    private JTextField txtField;

    public void start() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("MathGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //frame size
        frame.setSize(WIDTH, HEIGHT);
        gb = new GameBoard();
        frame.add(gb);
        txtField = new JTextField();
        txtField.setHorizontalAlignment(JTextField.RIGHT);
        txtField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    validateInput(txtField.getText());
                    txtField.setText("");

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        txtField.setFocusable(true);
        frame.getContentPane().add(txtField, BorderLayout.SOUTH);

        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e){
                /* Restrict input to only integers */


                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    validateInput(txtField.getText());
                    txtField.setText("");

                }
            }
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String... args) {
        MathGame game = new MathGame();
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> game.start());
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

    public void validateInput(String input){
        Equation equation = gb.getEquation();
        try {
            if(equation.getAnswer() == Integer.parseInt(input))
            {

            }
            else
            {
                System.out.println("YOU LOSE");
            }
        }
        catch(Exception e)
        {
            System.out.println("YOU LOSE");
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
