package soc.common.board.chits;

public class Chit10 extends AbstractChit
{
    private static final long serialVersionUID = 3847669375763234881L;

    @Override
    public Chit copy()
    {
        return new Chit10();
    }

    @Override
    public int getChance()
    {
        return 3;
    }

    @Override
    public int getNumber()
    {
        return 10;
    }

}
