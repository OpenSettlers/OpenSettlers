package soc.common.board.hexes;

import soc.common.board.Chit;

public class DesertHex extends AbstractHex
{
    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex copy()
    {
        DesertHex result = new DesertHex();

        result.setTerritory(territory);

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        return "DarkKhaki";
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.LandHex#isBuildableLand()
     */
    @Override
    public boolean isBuildableLand()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.LandHex#isBuildableSea()
     */
    @Override
    public boolean isBuildableSea()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.LandHex#isPartOfGame()
     */
    @Override
    public boolean isPartOfGame()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.LandHex#isPiratePlaceable()
     */
    @Override
    public boolean isPiratePlaceable()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.LandHex#isRobberPlaceable()
     */
    @Override
    public boolean isRobberPlaceable()
    {
        return true;
    }

    @Override
    public boolean canHaveChit()
    {
        return false;
    }

    @Override
    public boolean hasChit()
    {
        return false;
    }

    @Override
    public boolean hasResource()
    {
        return false;
    }

    @Override
    public Chit getChit()
    {
        return null;
    }

    @Override
    public Hex setChit(Chit chit)
    {
        return null;
    }

}
