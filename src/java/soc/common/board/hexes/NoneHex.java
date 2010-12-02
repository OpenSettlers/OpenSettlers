package soc.common.board.hexes;

/*
 * Represents a hex removed at gamestart, acting as design-time placeholder 
 */
public class NoneHex extends Hex
{

    /* Returns copy of a NoneHex
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex copy()
    {
        return new NoneHex();
    }

    /* NoneHex has white color
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        return "White";
    }

}
