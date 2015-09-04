import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreBoard extends JPanel{

    public GameBoard gameBoard;
    private List<Integer> scores;
    int x = 50;
    int y = 200;

    public ScoreBoard()
    {
        this.gameBoard = gameBoard;
        scores = new ArrayList();
        scores.add(0);
        scores.add(2);
        scores.add(3);
        scores.add(4);
        scores.add(6);
        scores.add(2);
    }

    public void sortScore()
    {
        Collections.sort(scores);
    }
    public Dimension getPreferredSize() {
        return new Dimension(gameBoard._boardWidth + 1, gameBoard._boardHeight + 1);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        sortScore();
        clear(g);
        drawHighScoreTitle(g);
        drawScore(g);

    }
    public void paint(Graphics g ) {
        super.paint(g);
    }

    public void clear(Graphics g)
    {
        repaint();
    }

    public void drawHighScoreTitle(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        FontRenderContext frc = g2.getFontRenderContext();
        Font f = new Font("Helvetica", 1, 40);
        String s = new String("HIGH SCORE");
        TextLayout textTl = new TextLayout(s, f, frc);
        Shape outline = textTl.getOutline(null);
        Rectangle outlineBounds = outline.getBounds();
        AffineTransform transform = g2.getTransform();
        transform.translate(gameBoard._boardWidth / 2 - (outlineBounds.width / 2), gameBoard._boardHeight / 4 + (outlineBounds.height / 2));
        g2.transform(transform);
        g2.setColor(Color.WHITE);
        g2.draw(outline);
    }

    public void drawScore(Graphics g)
    {
        int fontSize = 20;
        setBackground(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        g.setColor(Color.white);
        int i = 1;
        for(int s : scores)
        {
            g.drawString(("ONNI") + ":                                " + s + "" , x,  y - 20 * i);
            i++;
        }


    }
    public void addScore(int s)
    {
        scores.add(s);
    }
}
