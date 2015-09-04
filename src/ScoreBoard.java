import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard extends JPanel{

    private GameBoard gameBoard;
    private List<Integer> scores;
    int x = 200;
    int y = 200;

    public ScoreBoard(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
        scores = new ArrayList();
        scores.add(0);
    }
    public Dimension getPreferredSize() {
        return new Dimension(gameBoard._boardWidth + 1, gameBoard._boardHeight + 1);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawScore(g);

    }

    public void drawScore(Graphics g)
    {
        g.setColor(Color.yellow);
        for(int s : scores)
        {
            g.drawString(s + "" , x, y = y + 20);
        }


    }
    public void addScore(int s)
    {
        scores.add(s);
    }
}
