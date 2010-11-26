package soc.common.board.hexes;

public class DesertHex extends LandHex
{
    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex Copy()
    {
        // TODO Auto-generated method stub
        return new DesertHex();
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        // TODO Auto-generated method stub
        return "DarkKhaki";
    }

}
