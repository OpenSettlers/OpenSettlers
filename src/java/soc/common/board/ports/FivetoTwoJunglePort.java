package soc.common.board.ports;

import soc.common.annotations.OpenSettlers;

/*
 * Imaginary 2:5 port for diamonds.
 * To have fun with those (when devstack is empty) useless diamonds
 * 
 */
@OpenSettlers
public class FivetoTwoJunglePort extends AbstractPort
{
    private static final long serialVersionUID = 5264443650140189402L;

    @Override
    public Port copy()
    {
        return new FivetoTwoJunglePort();
    }

    @Override
    public String getColor()
    {
        return "DarkGray";
    }

}
