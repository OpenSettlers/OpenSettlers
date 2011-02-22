package soc.common.board.hexes;

import soc.common.board.resources.Resource;
import soc.common.board.resources.Timber;

public class TimberHex extends ResourceHex
{
    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#getResource()
     */
    @Override
    public Resource getResource()
    {
        return new Timber();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#copy()
     */
    @Override
    public Hex copy()
    {
        TimberHex timberHex = new TimberHex();
        timberHex.setChit(chit.copy());
        timberHex.setLocation(hexLocation);
        timberHex.setTerritory(territory);
        return timberHex;
    }

}
