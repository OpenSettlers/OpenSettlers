package soc.common.board.ports;

import soc.common.board.HexLocation;
import soc.common.board.RotationPosition;

public class PossiblePort extends AbstractPort
{
    private static final long serialVersionUID = 5430147957433152867L;

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
