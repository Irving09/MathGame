
/*
 * MathGame.java requires no other files.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    private GameBoard game;
    private JTextField txtField;

    public MathGame() {
        game = new GameBoard();
    }

    public void start() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("MathGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                    boolean isCorrect = validateInput(txtField.getText().trim());
                    if (isCorrect) {
                        // remove the equation from the game's list &&
                        // assign a new random equation from the game's list of equation
                        game.replaceCurrentEquation();
                        System.out.println("Correct!");
                    } else {
                        System.out.println("Wrong!");
                    }
                    System.out.println("Equations Left: " + game.getNumberOfEquations());
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

    public boolean validateInput(final String input){
        return game.getCurrentAnswerAsString().equals(input);
    }

    public static void main(final String... args) {
        MathGame game = new MathGame();
        SwingUtilities.invokeLater(() -> game.start());
    }
}
