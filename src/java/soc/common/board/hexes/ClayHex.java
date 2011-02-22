package soc.common.board.hexes;

import soc.common.board.resources.Clay;
import soc.common.board.resources.Resource;

public class ClayHex extends ResourceHex
{
    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#getResource()
     */
    @Override
    public Resource getResource()
    {
        return new Clay();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#copy()
     */
    @Override
    public Hex copy()
    {
        ClayHex clayHex = new ClayHex();
        clayHex.setChit(chit.copy());
        clayHex.setLocation(hexLocation);
        clayHex.setTerritory(territory);
        return clayHex;
    }
}
