import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    private static final int DEFAULT_ROWS = 20;
    private static final int DEFAULT_COLUMNS = 10;

    private int _nRows;
    private int _nColumns;
    private int _boardWidth;
    private int _boardHeight;
    private int _squareW;
    private int _squareH;
    private Equation _equation;

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
        _equation = new Equation();
    }

    public Dimension getPreferredSize() {
        return new Dimension(_boardWidth + 1, _boardHeight + 1);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
//        _equation.moveSquare();
//        g.drawString(_equation.getQuestion(), _equation.getX(), _equation.getY());
//        time = System.currentTimeMillis();
    }

    private void drawGrid(final Graphics g) {
        for (int col = 0; col < _nColumns; col++) {
            for (int row = 0; row < _nRows; row++) {
                paintSquare(_squareW * col, _squareH * row, g);
            }
        }
    }

    public void paintSquare(final int x, final int y, final Graphics canvas) {
        Square square = new Square();
        square.setX(x);
        square.setY(y);

        square.setColor(Color.WHITE);
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
}