package soc.common.board.chits;

public class Chit4 extends AbstractChit
{
    private static final long serialVersionUID = 8551201477474416255L;

    @Override
    public Chit copy()
    {
        return new Chit4();
    }

    @Override
    public int getChance()
    {
        return 3;
    }

    @Override
    public int getNumber()
    {
        return 4;
    }

}
