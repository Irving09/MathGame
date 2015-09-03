
/*
 * MathGame.java requires no other files.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MathGame {
    private static JFrame _frame;
    private GameBoard _game;
    private JTextField _txtField;

    public MathGame() {
        _game = new GameBoard();
        _txtField = new JTextField();
        addListeners();
    }

    public void start() {
        _frame = new JFrame("MathGame");
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.add(_game);
        _frame.getContentPane().add(_txtField, BorderLayout.SOUTH);
        _frame.pack();
        _frame.setVisible(true);
    }

    private void addListeners() {
        _txtField.setHorizontalAlignment(JTextField.RIGHT);
        _txtField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    boolean isCorrect = validateInput(_txtField.getText().trim());
                    if (isCorrect) {
                        _game.replaceCurrentEquation();
                        _game.incrementScore();
                        System.out.println("Correct!");
                        System.out.println(_game.currentScore());
                    } else {
                        System.out.println("Wrong!");
                    }
                    System.out.println("Equations Left: " + _game.getNumberOfEquations());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        _txtField.setFocusable(true);
    }

    public boolean validateInput(final String input){
        return _game.getCurrentAnswerAsString().equals(input);
    }

    public static void main(final String... args) {
        MathGame game = new MathGame();
        SwingUtilities.invokeLater(() -> game.start());
    }
}
