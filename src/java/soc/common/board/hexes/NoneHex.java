package soc.common.board.hexes;

import soc.common.board.HexLocation;

/*
 * Represents a hex removed at gamestart, acting as design-time placeholder 
 */
public class NoneHex extends AbstractHex
{

    /* Has no effect on a NoneHex, because a NoneHex cannot have a territory
     * @see soc.common.board.hexes.Hex#setLocation(soc.common.board.HexLocation)
     */
    @Override
    public AbstractHex setLocation(HexLocation hexLocation)
    {
        return this;
    }

    /* Returns copy of a NoneHex
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public AbstractHex copy()
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

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#isBuildableLand()
     */
    @Override
    public boolean isBuildableLand()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#isBuildableSea()
     */
    @Override
    public boolean isBuildableSea()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#isPartOfGame()
     */
    @Override
    public boolean isPartOfGame()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#isPiratePlaceable()
     */
    @Override
    public boolean isPiratePlaceable()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#isRobberPlaceable()
     */
    @Override
    public boolean isRobberPlaceable()
    {
        return false;
    }

}
