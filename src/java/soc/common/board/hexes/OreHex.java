package soc.common.board.hexes;

import soc.common.board.resources.Ore;
import soc.common.board.resources.Resource;

public class OreHex extends ResourceHex
{
    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#getResource()
     */
    @Override
    public Resource getResource()
    {
        return new Ore();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#copy()
     */
    @Override
    public Hex copy()
    {
        OreHex oreHex = new OreHex();
        oreHex.setChit(chit.copy());
        oreHex.setLocation(hexLocation);
        oreHex.setTerritory(territory);
        return oreHex;
    }
}
