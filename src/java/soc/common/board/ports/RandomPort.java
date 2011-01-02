package soc.common.board.ports;

/*
 * Placeholder for replacement of random ports at board preperation
 */
public class RandomPort extends AbstractPort
{
    private static final long serialVersionUID = 5964428508404257705L;

    @Override
    public Port copy()
    {
        return new RandomPort();
    }

    @Override
    public String getColor()
    {
        return "Gray";
    }
}
