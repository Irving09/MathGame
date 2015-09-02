import java.awt.*;


public class Equation {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;

    private int _xPos = 375;
    private int _yPos = 600;
    private int width = 20;
    private int height = 20;

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
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void moveSquare() {
        final int CURR_X = getX();
        final int CURR_Y = getY();
        final int CURR_W = getWidth();
        final int CURR_H = getHeight();

        //setX(x);
        setY(CURR_Y - 20);
    }
}
