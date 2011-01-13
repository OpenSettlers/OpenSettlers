package soc.common.board.pieces;

import soc.common.annotations.SeaFarers;
import soc.common.board.HexPoint;
import soc.common.board.territories.Territory;
import soc.common.game.VictoryPointItem;
import soc.common.game.player.GamePlayer;

@SeaFarers
public class IslandBonus extends AbstractPlayerPiece implements
        VictoryPointItem
{
    private static final long serialVersionUID = -4359339700965121307L;
    private Territory territory;
    private HexPoint location;

    /**
     * @return the location
     */
    public HexPoint getLocation()
    {
        return location;
    }

    /**
     * @return the territory
     */
    public Territory getTerritory()
    {
        return territory;
    }

    /**
     * @param territory
     *            the territory to set
     */
    public IslandBonus setTerritory(Territory territory)
    {
        this.territory = territory;

        return this;
    }

    @Override
    public int getVictoryPoints()
    {
        return 1;
    }

    @Override
    public boolean isStockPiece()
    {
        return false;
    }

    @Override
    public void addToPlayer(GamePlayer player)
    {
        player.getVictoryPoints().add(this);
    }

    @Override
    public void removeFromPlayer(GamePlayer player)
    {
        player.getVictoryPoints().remove(this);
    }

}
