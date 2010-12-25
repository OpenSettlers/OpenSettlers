package soc.common.board.ports;

/*
 * Placeholder for replacement of random ports at board preperation
 */
public class RandomPort extends AbstractPort
{

    @Override
    public Port copy()
    {
        return new RandomPort();
    }

}
