
public class GameStatus  {
    private Equation _currentEquation;
    private int lvl = 1;

    public GameStatus()
    {
    }

    public GameStatus(Equation equation)
    {
        _currentEquation = equation;
    }

    public void setEquation(Equation equation)
    {
        _currentEquation = equation;
    }

    public boolean HasEquationReachTheTop(int row)
    {
        return _currentEquation.getY() >= row;
    }

    public void checkLevel(int i)
    {
        if(i % 5 == 0)
        {
            lvl = i / 5 + 1;
        }
    }

    public int getLvl()
    {
        return lvl;
    }
}
