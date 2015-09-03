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

    public Equation generateEquation(int lvl) {
        switch (lvl)
        {
            case 1: addition();break;
            case 2: subtraction();break;
            case 3: multiplication();break;
            case 4: division();break;
            case 5: mix();break;
            default: quadratic();break;
        }
        reset();
        return this;
    }

    public void mix()
    {
        switch (ran.nextInt(4))
        {
            case 0:  addition();break;
            case 1: subtraction();break;
            case 2: multiplication();break;
            default: division();break;
        }
    }
    public void multiplication() {
        int a = ran.nextInt(10);
        int b = ran.nextInt(10);
        _answer = a *  b;
        _equation = a + " * " + b + " = x";

    }

    public void addition() {
        int a = ran.nextInt(10);
        int b = ran.nextInt(10);
        _answer = a +  b;
        _equation = a + " + " + b + " = x";

    }

    public void subtraction() {
        int a = ran.nextInt(10);
        int b = ran.nextInt(10);
        _answer = a -  b;
        _equation = a + " - " + b + " = x";

    }

    public void division() {
        int a = ran.nextInt(100);
        int b = ran.nextInt(99) +1;
        while(a % b != 0)
        {
            a = ran.nextInt(10);
            b = ran.nextInt(9) +1;
        }
        _answer = a /  b;
        _equation = a + " / " + b + " = x";

    }

    public void quadratic() {
        int a = ran.nextInt(4) + 1;
        int b = ran.nextInt(4) + 1;
        int c = (a*a) + (2*a*b) + (b*b);
        _equation = "a^2 + " + a*2 + "* a" + " + " + b*b + " = " + c;
        _answer = a;
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
