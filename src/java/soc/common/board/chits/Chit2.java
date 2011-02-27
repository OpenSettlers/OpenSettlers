package soc.common.board.chits;

public class Chit2 extends AbstractChit
{
    private static final long serialVersionUID = 289888659445647640L;

    @Override
    public Chit copy()
    {
        return new Chit2();
    }

    @Override
    public int getChance()
    {
        return 1;
    }

    @Override
    public int getNumber()
    {
        return 2;
    }
}
