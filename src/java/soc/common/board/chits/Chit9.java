package soc.common.board.chits;

public class Chit9 extends AbstractChit
{
    private static final long serialVersionUID = -2565610929859827018L;

    @Override
    public Chit copy()
    {
        return new Chit9();
    }

    @Override
    public int getNumber()
    {
        return 9;
    }

    @Override
    public int getChance()
    {
        return 4;
    }

}
