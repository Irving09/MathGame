import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameBoard extends JPanel {
    private static final int DEFAULT_ROWS = 28;
    private static final int DEFAULT_COLUMNS = 10;
    private static final int WINDOW_WIDTH = 420;
    private static final int WINDOW_HEIGHT = 600;

    private int nRows;
    private int nColumns;
    private int squareW = 40;
    private int squareH = 20;
    private int squareSeparation = 1;
    private int squareYSeparation = 1;
    Random ran = new Random(System.currentTimeMillis());
    private Equation _equation = new Equation();
    private long time = System.currentTimeMillis();

    public GameBoard() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS);
    }

    public GameBoard(int numRows, int numColumns) {
        nRows  = numRows;
        nColumns = numColumns;
    }

    public Dimension getPreferredSize() {
        return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Square[] squares = createSquares();
        for(Square s : squares)
        {
            s.paintSquare(g);
        }
        _equation.moveSquare();
        g.drawString(_equation.getQuestion(), _equation.getX(), _equation.getY());
        time = System.currentTimeMillis();
    }

    public Square[] createSquares()
    {
        Square[] squares = new Square[nRows*nColumns];
        for(int col = 0; col < nColumns; col++) {
            for(int row=0; row < nRows; row++) {
                int x = col + squareW * col;
                int y = squareH * row + row;

                //	create a single brick
                createSquare(x, y, col, row, squares);
            }
        }
        return squares;
    }

    public void createSquare(int x, int y, int r, int c, Square[] squares) {
        Square square = new Square();
        square.setX(x);
        square.setY(y);

        Color [] clr = {Color.white};

        square.setColor(clr[ran.nextInt(1)]);
        squares[c + (r * nRows)] = square;
    }

    public Equation getEquation()
    {
        return _equation;
    }
}