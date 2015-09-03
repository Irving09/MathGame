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
    private Equation _currentEquation;
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
        _currentEquation = _equations.get(generateRandomInt());
        _speed = DEFAULT_SPEED_IN_SECONDS;
        _timer = new Timer();
        _timer.schedule(new MovingTask(), 0, _speed * 1000);
    }

    private int generateRandomInt() {
        return new Random().nextInt(_equations.size()) % _equations.size();
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
        g.drawString(_currentEquation.getEquationAsString(), x, _boardHeight - Square.DEFAULT_HEIGHT * y - TEXT_OFFSET);
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

    private void removeCurrentEquationFromList() {
        if (_equations.contains(_currentEquation)) {
            _equations.remove(_currentEquation);
        }
    }

    public void replaceCurrentEquation() {
        removeCurrentEquationFromList();
        if (!_equations.isEmpty()) {
            _currentEquation = _equations.get(generateRandomInt());
        }
    }

    public int getNumberOfEquations() {
        return _equations.size();
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