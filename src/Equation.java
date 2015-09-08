import javax.swing.*;
import java.util.Random;

public class Equation extends JPanel {
    private int _y;
    private int _x;
    private String _equation;
    private int _answer;
    private int _answer2;
    private Random ran;
    public Equation() {
        ran = new Random(System.currentTimeMillis());
    }
    public Equation(final String equation, final int answer) {
        _equation = equation;
        _answer = answer;
        _answer2 = answer;
        _y = 0;
        _x = 5;
    }

    public String getEquationAsString() {
        return _equation;
    }

    public Equation generateEquation(int lvl) {
        switch (lvl)
        {
            case 1: addition();break;
            case 2: subtraction();break;
            case 3: multiplication();break;
            case 4: division();break;
            case 5: quadratic(); break;
            default: mix();break;
           // default: quadratic();break;
        }
        reset();
        return this;
    }

    public void mix()
    {
        switch (ran.nextInt(4))
        {
            case 0:
                addition();break;
            case 1:
                subtraction();break;
            case 2: multiplication();break;
            default: division();break;
        }
    }
    public void multiplication() {
        int a = ran.nextInt(10);
        int b = ran.nextInt(10);
        _answer = a *  b;
        _answer2 = _answer;
        _equation = a + " * " + b + " = x";

    }

    public void addition() {
        int a = ran.nextInt(10);
        int b = ran.nextInt(10);
        _answer = a +  b;
        _answer2 = _answer;
        _equation = a + " + " + b + " = x";

    }

    public void subtraction() {
        int a = ran.nextInt(10);
        int b = ran.nextInt(10);
        _answer = a -  b;
        _answer2 = _answer;
        _equation = a + " - " + b + " = x";

    }

    public void division() {
        int a = ran.nextInt(100);
        int b = ran.nextInt(99) +1;
        while(a % b != 0)
        {
            a = ran.nextInt(100);
            b = ran.nextInt(99) +1;
        }
        _answer = a /  b;
        _answer2 = _answer;
        _equation = a + " / " + b + " = x";

    }

    public void quadratic() {
        int a = ran.nextInt(9) + 1;
        int b = ran.nextInt(399) + 1;
        int c = ran.nextInt(9) + 1;
        while(b * b != 4 * a * c)
        {
            a = ran.nextInt(9) + 1;
            b = ran.nextInt(399) + 1;
            c = ran.nextInt(9) + 1;
        }
        _equation = "x^2 + " + b + "x" + " + " + c + " = " + ((a*a) + (b * a) + c);
        _answer = a;
        _answer2 = (ran.nextInt(99) + 1) * -1;
        while((_answer2*_answer2) + (b * _answer2) + c != ((a*a) + (b * a) + c))
        {
            _answer2 = (ran.nextInt(99) + 1) * -1;
        }
    }

    public int getAnswerAsInt() {
        return _answer;
    }

    public String [] getAnswerAsString() {
        String [] s = new String [2];
        s[0] = "" + _answer;
        s[1] = "" + _answer2;
        return s;
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

    public void moveUp(int s) {
        _y = _y + s;
    }

    public void moveUp() {
        _y++;
    }

    public String toString() {
        return _equation + " : " + _answer;
    }
}
