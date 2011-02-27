package soc.common.board.chits;

public class Chit6 extends AbstractChit
{
    private static final long serialVersionUID = 7264719532207840869L;

    @Override
    public Chit copy()
    {
        return new Chit6();
    }

    @Override
    public int getChance()
    {
        return 6;
    }

    @Override
    public int getNumber()
    {
        return 5;
    }

}
