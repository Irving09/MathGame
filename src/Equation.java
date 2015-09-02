import java.awt.*;


public class Equation {
    String equations1 = "8 * 8";

    private int xPos = 375;
    private int yPos = 600;
    private int width = 20;
    private int height = 20;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;

    public void setX(int xPos){
        this.xPos = xPos;
    }

    public int getX(){
        return xPos;
    }

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public int getY(){
        return yPos;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    private void moveSquare() {
        final int CURR_X = getX();
        final int CURR_Y = getY();
        final int CURR_W = getWidth();
        final int CURR_H = getHeight();

        //setX(x);
        setY(CURR_Y - 20);
    }

    public void display(Graphics g){
        moveSquare();
        g.drawString(equations1,xPos ,yPos);
    }
}
