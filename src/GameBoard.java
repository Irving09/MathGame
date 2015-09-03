import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class GameBoard extends JPanel {
    private static final int DEFAULT_ROWS = 20;
    private static final int DEFAULT_COLUMNS = 10;
    private static final int TEXT_OFFSET = 10;
    private static final int DEFAULT_SPEED_IN_SECONDS = 2;

    private int _nRows;
    private int _nColumns;
    private int _boardWidth;
    private int _boardHeight;
    private int _squareW;
    private int _squareH;
    private int _speed;
    private List<Equation> _equations;
    private Equation _equation;
    private Timer _timer;

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
        _equations = generateDefaultEquations();
        _equation = _equations.get(new Random().nextInt(_equations.size()) % _equations.size());
        _speed = DEFAULT_SPEED_IN_SECONDS;
        _timer = new Timer();
        _timer.schedule(new MovingTask(), 0, _speed * 1000);
    }

    public Dimension getPreferredSize() {
        return new Dimension(_boardWidth + 1, _boardHeight + 1);
    }

    public List<Equation> generateDefaultEquations() {
        List<Equation> equations = new ArrayList<Equation>();
        equations.add(new Equation("0 * 0", 64));
        equations.add(new Equation("1 * 1", 64));
        equations.add(new Equation("2 * 2", 64));
        equations.add(new Equation("3 * 3", 64));
        equations.add(new Equation("4 * 4", 64));
        equations.add(new Equation("5 * 5", 64));
        equations.add(new Equation("6 * 6", 64));
        equations.add(new Equation("7 * 7", 64));
        equations.add(new Equation("8 * 8", 64));
        equations.add(new Equation("9 * 9", 64));
        return equations;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawEquation(g, _equation.getX(), _equation.getY());
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
        g.drawString(_equation.getEquationAsString(), x, _boardHeight - Square.DEFAULT_HEIGHT * y - TEXT_OFFSET);
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

    public Equation getEquation() {
        return _equation;
    }

    public void removeEquationFromList(int index) {
        try {
            _equations.remove(index);
        } catch(IndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    class MovingTask extends TimerTask {
        public void run() {
            _equation.moveUp();
            repaint();
        }
    }
}