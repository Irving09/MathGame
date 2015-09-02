import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;

class GameBoard extends JPanel {


    private static final int WIDTH = 420;
    private static final int HEIGHT = 600;
    private int nRows = 28;
    private int nColumns = 10;
    private int squareW = 40;
    private int squareH = 20;
    private int squareSeparation = 1;
    private int squareYSeparation = 1;
    Random ran = new Random(System.currentTimeMillis());
    private Equation equation = new Equation();
    private long time = System.currentTimeMillis();
    Square [] squares = new Square[nRows*nColumns];

    public GameBoard() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }



    public Dimension getPreferredSize() {
        return new Dimension(WIDTH,HEIGHT);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSquares();
        for(Square s : squares)
        {
            s.paintSquare(g);
        }
        if(5000 > System.currentTimeMillis() - time ) {
            equation.display(g);
            time = System.currentTimeMillis();
        }

    }

    public void drawSquares()
    {
        for(int r = 0; r < nColumns; r++)
        {
            for(int c=0; c < nRows; c++)
            {
                int x = squareSeparation*(r) + (squareW *r)+4;
                int y = squareYSeparation + (squareH * c)+squareSeparation*c;

                //	draw a single brick
                drawSquare(x, y, r ,c);

            }
        }
    }

    public void drawSquare(int x, int y, int r, int c)
    {
        Square square = new Square();
        square.setX(x);
        square.setY(y);

        Color [] clr = {Color.white};

        square.setColor(clr[ran.nextInt(1)]);
        squares[c + (r * nRows)] = square;
    }
}