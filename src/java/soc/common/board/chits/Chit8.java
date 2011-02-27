package soc.common.board.chits;

public class Chit8 extends AbstractChit
{
    private static final long serialVersionUID = -2593827639221299571L;

    @Override
    public Chit copy()
    {
        return new Chit8();
    }

    @Override
    public int getChance()
    {
        return 5;
    }

    @Override
    public int getNumber()
    {
        return 8;
    }

}
