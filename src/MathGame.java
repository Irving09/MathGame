
/*
 * MathGame.java requires no other files.
 */


import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MathGame implements ActionListener {
    private static JFrame _frame;
    private GameBoard _game;
    private JTextField _txtField;
    private JProgressBar _progressBar;
    private JButton _button;
    private int progressLevel = 0;
    static MathGame game;
    private static ScoreBoard _scoreBoard = new ScoreBoard();
    private JPanel _cards;
    Timer timer;
    int speed = 60;

    public MathGame()  {
        _game = new GameBoard();
        _txtField = new JTextField();
        _progressBar = new JProgressBar(JProgressBar.VERTICAL);
        _progressBar.setValue(progressLevel);
        _button = new JButton("ScoreBoard");
        _button.setActionCommand("score");
        _scoreBoard.gameBoard = _game;
        _game.scoreBoard = _scoreBoard;
        _cards = new JPanel(new CardLayout());
        _cards.add(_game, "game");
        _cards.add(_scoreBoard, "scoreBoard");
        timer = new Timer(speed, this);

    }

    public void start() {
        _frame = new JFrame("MathGame");
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.add(_cards);

        _frame.getContentPane().add(_progressBar, BorderLayout.EAST);
        _frame.getContentPane().add(_button, BorderLayout.NORTH);
        _frame.getContentPane().add(_txtField, BorderLayout.SOUTH);
        addListeners();
        _frame.pack();
        _frame.setVisible(true);
        timer.setInitialDelay(2000);
        timer.start();
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
                        _game.incrementScore();
                        _game.replaceCurrentEquation();
                        System.out.println("Correct!");
                        System.out.println(_game.currentScore());
                        progressLevel++;
                        _progressBar.setValue(progressLevel);
                        boolean isLeveledUp = _game.gameStatus.checkLevel(progressLevel);


                    } else {
                        System.out.println("Wrong!");
                        _game.penalize();
                        System.out.println(_game.currentScore());
                        progressLevel--;
                        _progressBar.setValue(progressLevel);
                    }
                    _txtField.setText("");
                }

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                    timer.stop();
                    increaseSpeed(_game.gameStatus.getLvl());
                    game = new MathGame();
                    _scoreBoard.hasScoreAdded = false;
                    game.start();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        _txtField.setFocusable(true);
        _button.addActionListener(e -> {

            CardLayout cl = (CardLayout) (_cards.getLayout());
            if (_button.getText().equals("ScoreBoard")) {
                timer.stop();
                cl.show(_cards, "scoreBoard");
                _button.setText("Resume");
            } else {
                cl.show(_cards, "game");
                _button.setText("ScoreBoard");
                timer.start();
            }
            _frame.getContentPane().add(_button, BorderLayout.NORTH);

            _frame.revalidate();
            _frame.repaint();
        });

        _txtField.setFocusable(true);

        _frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                _txtField.requestFocusInWindow();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        _game._currentEquation.moveUp();
        increaseSpeed(_game.gameStatus.getLvl());
        try {
            if(!_game.scoreBoard.hasScoreAdded && !_game.gameStatus.isAlive()){
                _game.scoreBoard.addScore(_game._currentScore);
            }
        }catch(Exception err){}

        _game._equations.moveUp();
        _game.repaint();
    }

    public void increaseSpeed(int lvl)
    {
        speed = 60 - (lvl * 8);
        if(speed< 1)
        {
            speed = 1;
        }
        timer.setDelay(speed);
    }
    public boolean validateInput(final String input){
        return _game.getCurrentAnswerAsString()[0].equals(input) || _game.getCurrentAnswerAsString()[1].equals(input);
    }

    public static void main(final String... args) {
        game = new MathGame();
        SwingUtilities.invokeLater(() -> game.start());
    }
}
