import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class Equation {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private static final int DEFAULT_SPEED_IN_SECONDS = 2;

    private int _y;
    private Timer _timer;
    private Toolkit _toolkit;

    String equations[] = new String[1];
    int answers[] = new int[1];

    public Equation(){
        equations[0] = "8 * 8";
        answers[0] = 64;
        _y = 0;
    }

    private void setSpeed() {

    }

    public String getQuestion() {
        return equations[0];
    }

    public int getY() {
        return _y;
    }

    public int getAnswer() {
        return answers[0];
    }

    public void moveUp() {
        _y++;
    }
}
