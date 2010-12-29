package soc.common.board.ports;

import soc.common.board.HexLocation;
import soc.common.board.RotationPosition;

public class PossiblePort extends AbstractPort
{
    public PossiblePort()
    {
    }

    public PossiblePort(HexLocation hexLocation,
            RotationPosition rotationPosition)
    {
        super(hexLocation, rotationPosition);
    }

    @Override
    public Port copy()
    {
        return new PossiblePort();
    }

    @Override
    public String getColor()
    {
        return "White";
    }
}
