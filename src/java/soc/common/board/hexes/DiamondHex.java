package soc.common.board.hexes;

import soc.common.board.resources.Diamond;
import soc.common.board.resources.Resource;

public class DiamondHex extends ResourceHex
{
    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#getResource()
     */
    @Override
    public Resource getResource()
    {
        return new Diamond();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#copy()
     */
    @Override
    public Hex copy()
    {
        DiamondHex diamondHex = new DiamondHex();
        diamondHex.setChit(chit.copy());
        diamondHex.setLocation(hexLocation);
        diamondHex.setTerritory(territory);
        return diamondHex;
    }
}
