package soc.common.board.hexes;

import soc.common.board.resources.Clay;
import soc.common.board.resources.Resource;

public class SheepHex extends ResourceHex
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
        SheepHex sheepHex = new SheepHex();
        sheepHex.setChit(chit.copy());
        sheepHex.setLocation(hexLocation);
        sheepHex.setTerritory(territory);
        return sheepHex;
    }
}
