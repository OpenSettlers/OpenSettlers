package soc.common.board.hexes;

/*
 * Represents a hex removed at gamestart, acting as design-time placeholder 
 */
public class NoneHex extends Hex
{

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex Copy()
    {
        // TODO Auto-generated method stub
        return new NoneHex();
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
