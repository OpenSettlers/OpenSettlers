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
