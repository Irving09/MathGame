import java.awt.*;

public class Square{
    public static final int DEFAULT_WIDTH = 40;

    public static final int DEFAULT_HEIGHT = 40;

    private static final int DEFAULT_X = 0;

    private static final int DEFAULT_Y = 0;

    private int _xPos = 0;

    private int _yPos = 0;

    private int _width = DEFAULT_WIDTH;

    private int _height = DEFAULT_HEIGHT;

    private Color color = Color.RED;

    public void setX(int xPos){
        _xPos = xPos;
    }

    public int getX(){
        return _xPos;
    }

    public void setY(int yPos){
        _yPos = yPos;
    }

    public int getY(){
        return _yPos;
    }

    public int getWidth(){
        return _width;
    }

    public int getHeight(){
        return _height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        color = c;
    }

    public void paintSquare(Graphics g){
        g.setColor(color);
        g.fillRect(_xPos, _yPos, _width, _height);
        g.setColor(Color.BLACK);
        g.drawRect(_xPos, _yPos, _width, _height);
    }
}