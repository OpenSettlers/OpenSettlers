package soc.common.board.hexes;

public class DesertHex extends LandHex
{
    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex copy()
    {
        DesertHex result = new DesertHex();
        
        result.setTerritory(territory);
        
        return result;
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        return "DarkKhaki";
    }

}
