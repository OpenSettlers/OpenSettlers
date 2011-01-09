package soc.common.board.ports;

import soc.common.board.HexLocation;
import soc.common.board.RotationPosition;

/*
 * Placeholder for replacement of random ports at board preperation
 */
public class RandomPort extends AbstractPort
{
    private static final long serialVersionUID = 5964428508404257705L;

    public RandomPort(HexLocation hexLocation, RotationPosition rotationPosition)
    {
        super(hexLocation, rotationPosition);
    }

    public RandomPort()
    {

    }

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
