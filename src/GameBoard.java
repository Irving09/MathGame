import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.Timer;

public class GameBoard extends JPanel {
    private static final int DEFAULT_ROWS = 20;
    private static final int DEFAULT_COLUMNS = 10;
    private static final int TEXT_OFFSET = 10;
    private static final int DEFAULT_SPEED_IN_SECONDS = 1;

    private int _nRows;
    private int _nColumns;
    int _boardWidth;
    int _boardHeight;
    private int _squareW;
    private int _squareH;
    private int _speed;
    private int _currentScore;
    private Equation _currentEquation;
    Timer _timer;
    Equation _equations = new Equation();
    GameStatus gameStatus = new GameStatus();
    ScoreBoard scoreBoard;

    public GameBoard() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS);
    }

    public GameBoard(int numRows, int numColumns) {
        _nRows = numRows;
        _nColumns = numColumns;
        _squareH = Square.DEFAULT_HEIGHT;
        _squareW = Square.DEFAULT_WIDTH;
        _boardWidth = _nColumns * _squareW;
        _boardHeight = _nRows * _squareH;
        _currentEquation = _equations.generateEquation(gameStatus.getLvl());
        gameStatus = new GameStatus(_currentEquation);
        _speed = DEFAULT_SPEED_IN_SECONDS;
        _currentScore = 0;
        _timer = new Timer();
        scheduleTimer();

    }

    public void scheduleTimer(){
        _timer = new Timer();
        _timer.schedule(new MovingTask(), 0, _speed * 1000);
    }

    public Dimension getPreferredSize() {
        return new Dimension(_boardWidth + 1, _boardHeight + 1);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawEquation(g, _currentEquation.getX(), _currentEquation.getY());
        drawScore(g, 0, 0);
        drawLevel(g);
        if(gameStatus.HasEquationReachTheTop(_nRows)){
            drawYourScore(g);
            _timer.cancel();

        }
    }

    public void drawYourScore(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        FontRenderContext frc = g2.getFontRenderContext();
        Font f = new Font("Helvetica", 1, 40);
        String s = new String("YOUR SCORE: ");
        TextLayout textTl = new TextLayout(s, f, frc);

        g2.setColor(Color.red);
        textTl.draw(g2, 100, 200);

        TextLayout textTl2 = new TextLayout("" + _currentScore, f, frc);


        textTl2.draw(g2, 150, 250);

    }

    private void drawGrid(final Graphics g) {
        for (int col = 0; col < _nColumns; col++) {
            for (int row = 0; row < _nRows; row++) {
                paintSquare(_squareW * col, _squareH * row, g);
            }
        }
    }

    private void drawEquation(final Graphics g, final int x, final int y) {
        g.setColor(Color.CYAN);
        g.drawString(_currentEquation.getEquationAsString(), _boardWidth - Square.DEFAULT_WIDTH * x - TEXT_OFFSET, _boardHeight - Square.DEFAULT_HEIGHT * y - TEXT_OFFSET + Square.DEFAULT_HEIGHT);
    }

    public void drawScore(final Graphics g, final int x, final int y) {
        g.setColor(Color.yellow);
        g.drawString("Score: " + _currentScore, 25, 20);
    }

    public void drawLevel(final Graphics g) {
        g.setColor(Color.yellow);
        g.drawString("Level: " + gameStatus.getLvl(), 25, 40);
    }

    public void paintSquare(final int x, final int y, final Graphics canvas) {
        Square square = new Square();
        square.setX(x);
        square.setY(y);

        square.setColor(Color.DARK_GRAY);
        square.paintSquare(canvas);
    }

    public String [] getCurrentAnswerAsString() {
        return _currentEquation.getAnswerAsString();
    }

    public void incrementScore() {
        _currentScore = _currentScore + (gameStatus.getLvl() * (_nRows - _currentEquation.getY()));
    }

    public int currentScore() {
        return _currentScore;
    }

    public void replaceCurrentEquation() {
        _currentEquation = _equations.generateEquation(gameStatus.getLvl());
        gameStatus.setEquation(_currentEquation);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current Equation: ");
        sb.append(_currentEquation.toString());
        sb.append("\n");
        sb.append("Equations: ");
        sb.append(_equations);
        sb.append("\n");
        sb.append("boardWidth: ");
        sb.append(_boardWidth);
        sb.append("\n");
        sb.append("boardHeight: ");
        sb.append(_boardHeight);
        return sb.toString();
    }

    class MovingTask extends TimerTask {
        public void run() {
            _currentEquation.moveUp();
            try {
                if(!scoreBoard.hasScoreAdded && !gameStatus.isAlive()){
                    scoreBoard.addScore(_currentScore);
                }
            }catch(Exception err){}

            repaint();
        }
    }
}