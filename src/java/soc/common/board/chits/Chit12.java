package soc.common.board.chits;

public class Chit12 extends AbstractChit
{
    private static final long serialVersionUID = -2356981320798618769L;

    @Override
    public Chit copy()
    {
        return new Chit12();
    }

    @Override
    public int getChance()
    {
        return 1;
    }

    @Override
    public int getNumber()
    {
        return 12;
    }

}
