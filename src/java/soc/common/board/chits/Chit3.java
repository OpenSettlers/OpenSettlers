package soc.common.board.chits;

public class Chit3 extends AbstractChit
{
    private static final long serialVersionUID = -7935501769616769044L;

    @Override
    public Chit copy()
    {
        return new Chit3();
    }

    @Override
    public int getChance()
    {
        return 2;
    }

    @Override
    public int getNumber()
    {
        return 3;
    }

}
