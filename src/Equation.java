import javax.swing.*;

public class Equation extends JPanel {
    private int _y;
    private int _x;
    private String _equation;
    private int _answer;

    public Equation(final String equation, final int answer) {
        _equation = equation;
        _answer = answer;
        _y = 0;
        _x = 5;
    }

    public String getEquationAsString() {
        return _equation;
    }

    public int getAnswerAsInt() {
        return _answer;
    }

    public String getAnswerAsString() {
        return Integer.toString(_answer);
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
