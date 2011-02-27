package soc.common.board.chits;

public class RandomChit implements Chit
{

    @Override
    public Chit copy()
    {
        return new RandomChit();
    }

    @Override
    public int getChance()
    {
        return 0;
    }

    @Override
    public int getNumber()
    {
        return 0;
    }

    @Override
    public boolean isRed()
    {
        return false;
    }

}
