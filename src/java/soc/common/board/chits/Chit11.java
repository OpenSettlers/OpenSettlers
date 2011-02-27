package soc.common.board.chits;

public class Chit11 extends AbstractChit
{
    private static final long serialVersionUID = -418107127761907817L;

    @Override
    public Chit copy()
    {
        return new Chit11();
    }

    @Override
    public int getChance()
    {
        return 2;
    }

    @Override
    public int getNumber()
    {
        return 11;
    }

}
