package soc.common.board.hexes;

import soc.common.annotations.SeaFarers;

@SeaFarers
public class DiscoveryHex extends Hex
{

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex Copy()
    {
        // TODO Auto-generated method stub
        return new DiscoveryHex();
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        // TODO Auto-generated method stub
        return "White";
    }

}
