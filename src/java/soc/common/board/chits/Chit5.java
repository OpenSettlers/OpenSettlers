package soc.common.board.chits;

public class Chit5 extends AbstractChit
{
    private static final long serialVersionUID = -2537731750828852429L;

    @Override
    public Chit copy()
    {
        return new Chit5();
    }

    @Override
    public int getChance()
    {
        return 4;
    }

    @Override
    public int getNumber()
    {
        return 5;
    }

}
