package soc.common.board.ports;

import soc.common.annotations.OpenSettlers;
import soc.common.board.resources.Diamond;
import soc.common.board.resources.Resource;
import soc.common.views.meta.Meta;

/*
 * Imaginary 2:5 port for diamonds.
 * To have fun with those (when devstack is empty) useless diamonds
 * 
 */
@OpenSettlers
public class FivetoTwoJunglePort extends AbstractPort
{
    private static final long serialVersionUID = 5264443650140189402L;
    private static Diamond diamond = new Diamond();

    @Override
    public Port copy()
    {
        return new FivetoTwoJunglePort();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.ports.AbstractPort#getInAmount()
     */
    @Override
    public int getInAmount()
    {
        return 5;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.ports.AbstractPort#getOutAmount()
     */
    @Override
    public int getOutAmount()
    {
        return 2;
    }

    @Override
    public String getColor()
    {
        return "DarkGray";
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasResource()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.ports.AbstractPort#getResource()
     */
    @Override
    public Resource getResource()
    {
        return diamond;
    }
}