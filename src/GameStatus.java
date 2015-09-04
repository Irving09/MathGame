
public class GameStatus  {
    private Equation _currentEquation;
    private int lvl = 1;
    private boolean alive = true;

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

    public boolean HasEquationReachTheTop(final int boardHeight)
    {
        alive = _currentEquation.getY() < boardHeight;
        return !alive;
    }

    public boolean isAlive()
    {
        return alive;
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
