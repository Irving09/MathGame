
public class GameStatus  {
    private Equation _currentEquation;

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


}
