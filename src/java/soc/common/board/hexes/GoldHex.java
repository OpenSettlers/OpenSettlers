package soc.common.board.hexes;

import soc.common.board.resources.Gold;
import soc.common.board.resources.Resource;

public class GoldHex extends ResourceHex
{
    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#getResource()
     */
    @Override
    public Resource getResource()
    {
        return new Gold();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#copy()
     */
    @Override
    public Hex copy()
    {
        GoldHex goldHex = new GoldHex();
        goldHex.setChit(chit.copy());
        goldHex.setLocation(hexLocation);
        goldHex.setTerritory(territory);
        return goldHex;
    }
}