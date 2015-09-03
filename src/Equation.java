import javax.swing.*;
import java.util.Random;

public class Equation extends JPanel {
    private int _y;
    private int _x;
    private String _equation;
    private int _answer;
    private Random ran;
    public Equation() {
        ran = new Random(System.currentTimeMillis());
    }
    public Equation(final String equation, final int answer) {
        _equation = equation;
        _answer = answer;
        _y = 0;
        _x = 5;
    }

    public String getEquationAsString() {
        return _equation;
    }

    public Equation generateEquation() {
        muliplication();
        reset();
        return this;
    }

    public void muliplication() {
        int a = ran.nextInt(10);
        int b = ran.nextInt(10);
        _answer = a *  b;
        _equation = a + " * " + b + " = x";

    }

    public void quadratic() {
        int a = ran.nextInt(9) + 1;
        int b = ran.nextInt(10);
        int c = ran.nextInt(10);
        _answer = a* (c * c) + b;
        _equation = a + "(x^2) + " + b + " = " + _answer;
        _answer = c;
    }

    public int getAnswerAsInt() {
        return _answer;
    }

    public String getAnswerAsString() {
        return Integer.toString(_answer);
    }
    public void reset() {
        _y = 0;
        _x = 5;
    }
    public int getY() {
        return _y;
    }

    public int getX() {
        return _x;
    }

    public void moveUp() {
        _y++;
    }

    public String toString() {
        return _equation + " : " + _answer;
    }
}
