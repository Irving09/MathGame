
/*
 * MathGame.java requires no other files.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.List;

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
        GameBoard game = new GameBoard();
        //Create and set up the window.
        frame = new JFrame("MathGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //frame size
        frame.add(game);
        JTextField txtField = new JTextField();
        txtField.setHorizontalAlignment(JTextField.RIGHT);
        txtField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println(txtField.getText());
                    txtField.setText(gb.getEquation().toString());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        txtField.setFocusable(true);
        frame.getContentPane().add(txtField, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    public void validateInput(String input){
        Equation equation = gb.getEquation();
        try {
            if(equation.getAnswerAsInt() == Integer.parseInt(input)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong!");
            }
        }
        catch(Exception e) {
            System.out.println("Wrong format");
        }
    }

    public static void main(final String... args) {
        MathGame game = new MathGame();
        SwingUtilities.invokeLater(() -> game.start());
    }
}
