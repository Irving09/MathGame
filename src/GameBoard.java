import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    private static final int DEFAULT_ROWS = 20;
    private static final int DEFAULT_COLUMNS = 10;
    private static final int WINDOW_WIDTH = 420;
    private static final int WINDOW_HEIGHT = 600;
    private static final int DEFAULT_SQUARE_WIDTH = 40;
    private static final int DEFAULT_SQUARE_HEIGHT = 40;

    private int _nRows;
    private int _nColumns;
    private int _boardWidth;
    private int _boardHeight;
    private int squareW = DEFAULT_SQUARE_WIDTH;
    private int squareH = DEFAULT_SQUARE_HEIGHT;
    private Equation _equation = new Equation();
    private long time = System.currentTimeMillis();

    public GameBoard() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS);
    }

    public GameBoard(int numRows, int numColumns) {
        _nRows = numRows;
        _nColumns = numColumns;
        _boardWidth = _nColumns * Square.DEFAULT_WIDTH;
        _boardHeight = _nRows * Square.DEFAULT_HEIGHT;
    }

    public Dimension getPreferredSize() {
        return new Dimension(_boardWidth + 1, _boardHeight + 1);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
//        _equation.moveSquare();
//        g.drawString("Equation", _equation.getX(), _equation.getY());
//        time = System.currentTimeMillis();
    }

    private void drawGrid(final Graphics g) {
        for (int col = 0; col < _nColumns; col++) {
            for (int row = 0; row < _nRows; row++) {
                paintSquare(squareW * col, squareH * row, g);
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

    public Square[] createSquares()
    {
        Square[] squares = new Square[_nRows * _nColumns];
        for(int col = 0; col < _nColumns; col++) {
            for(int row=0; row < _nRows; row++) {
                int x = col + squareW * col;
                int y = squareH * row + row;

                //	create a single brick
                createSquare(x, y, col, row, squares);
            }
        }
        return squares;
    }

    public void createSquare(final int x, final int y, int r, int c, Square[] squares) {
        Square square = new Square();
        square.setX(x);
        square.setY(y);

        square.setColor(Color.WHITE);
        squares[c + (r * _nRows)] = square;
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
}