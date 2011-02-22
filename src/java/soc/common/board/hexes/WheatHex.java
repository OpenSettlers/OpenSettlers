package soc.common.board.hexes;

import soc.common.board.resources.Resource;
import soc.common.board.resources.Wheat;

public class WheatHex extends ResourceHex
{
    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#getResource()
     */
    @Override
    public Resource getResource()
    {
        return new Wheat();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#copy()
     */
    @Override
    public Hex copy()
    {
        WheatHex wheatHex = new WheatHex();
        wheatHex.setChit(chit.copy());
        wheatHex.setLocation(hexLocation);
        wheatHex.setTerritory(territory);
        return wheatHex;
    }
}
