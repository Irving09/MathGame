import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class GameBoard extends JPanel {
    private static final int DEFAULT_ROWS = 20;
    private static final int DEFAULT_COLUMNS = 10;
    private static final int TEXT_OFFSET = 10;
    private static final int DEFAULT_SPEED_IN_SECONDS = 1;

    private int _nRows;
    private int _nColumns;
    private int _boardWidth;
    private int _boardHeight;
    private int _squareW;
    private int _squareH;
    private int _speed;
    private int _currentScore;
    private Equation _currentEquation;
    private Timer _timer;
    Equation _equations = new Equation();
    GameStatus gameStatus;

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
        _currentEquation = _equations.generateEquation();
        gameStatus = new GameStatus(_currentEquation);
        _speed = DEFAULT_SPEED_IN_SECONDS;
        _currentScore = 0;
        _timer = new Timer();
        _timer.schedule(new MovingTask(), 0, _speed * 1000);
    }

    public Dimension getPreferredSize() {
        return new Dimension(_boardWidth + 1, _boardHeight + 1);
    }

    public List<Equation> generateDefaultEquations() {
        List<Equation> equations = new ArrayList<Equation>();
        equations.add(new Equation("0 * 0", 0));
        equations.add(new Equation("1 * 1", 1));
        equations.add(new Equation("2 * 2", 4));
        equations.add(new Equation("3 * 3", 9));
        equations.add(new Equation("4 * 4", 16));
        equations.add(new Equation("5 * 5", 25));
        equations.add(new Equation("6 * 6", 36));
        equations.add(new Equation("7 * 7", 49));
        equations.add(new Equation("8 * 8", 64));
        equations.add(new Equation("9 * 9", 81));
        return equations;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawEquation(g, _currentEquation.getX(), _currentEquation.getY());
        drawScore(g, 0, 0);
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
        String s = new String("YOU SCORE: ");
        TextLayout textTl = new TextLayout(s, f, frc);
        AffineTransform transform = new AffineTransform();
        Shape outline = textTl.getOutline(null);
        Rectangle outlineBounds = outline.getBounds();
        transform = g2.getTransform();
        transform.translate(_boardWidth / 2 - (outlineBounds.width / 2), _boardHeight / 2
                + (outlineBounds.height / 2));
        g2.transform(transform);
        g2.setColor(Color.red);
        g2.draw(outline);
        TextLayout textTl2 = new TextLayout("" + _currentScore, f, frc);
        Shape outline2 = textTl2.getOutline(null);
        //outline2.getBounds2D().setRect(1, 50, outline2.getBounds2D().getWidth(), outline2.getBounds2D().getHeight());
        transform.translate(_boardWidth/2, 40);
        g2.setTransform(transform);
        g2.draw(outline2);
        g2.setClip(outline);
        g2.setClip(outline2);
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
        g.drawString(_currentEquation.getEquationAsString(), _boardWidth - Square.DEFAULT_WIDTH * x - TEXT_OFFSET, _boardHeight - Square.DEFAULT_HEIGHT * y  - TEXT_OFFSET + Square.DEFAULT_HEIGHT);
    }

    public void drawScore(final Graphics g, final int x, final int y) {
        g.setColor(Color.CYAN);
        g.drawString(Integer.toString(_currentScore), _boardWidth - Square.DEFAULT_WIDTH * x - TEXT_OFFSET, _boardHeight - Square.DEFAULT_HEIGHT * y - TEXT_OFFSET);
    }

    public void paintSquare(final int x, final int y, final Graphics canvas) {
        Square square = new Square();
        square.setX(x);
        square.setY(y);

        square.setColor(Color.DARK_GRAY);
        square.paintSquare(canvas);
    }

    public int getRows() {
        return _nRows;
    }

    public int getColumns() {
        return _nColumns;
    }

    public int getBoardWidth() {
        return _boardWidth;
    }

    public int getBoardHeight() {
        return _boardHeight;
    }

    public String getCurrentEquationAsString() {
        return _currentEquation.getEquationAsString();
    }

    public Equation getCurrentEquation() {
        return _currentEquation;
    }

    public int getCurrentAnswerAsInt() {
        return _currentEquation.getAnswerAsInt();
    }

    public String getCurrentAnswerAsString() {
        return _currentEquation.getAnswerAsString();
    }

    public void incrementScore() {
        _currentScore++;
    }

    public int currentScore() {
        return _currentScore;
    }

    public void removeEquationFromListByIndex(final int index) {
        try {
            _equations.remove(index);
        } catch(IndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    public void removeEquationFromListByObject(final Equation toBeRemoved) {
        try {
            _equations.remove(toBeRemoved);
        } catch(IndexOutOfBoundsException e) {
            System.err.println(e.getStackTrace());
        }
    }

    public void replaceCurrentEquation() {
        _currentEquation = _equations.generateEquation();
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
            repaint();
        }
    }
}