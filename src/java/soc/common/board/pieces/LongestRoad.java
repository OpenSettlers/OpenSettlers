package soc.common.board.pieces;

import soc.common.board.routing.Route;
import soc.common.game.VictoryPointItem;
import soc.common.game.player.GamePlayer;

public class LongestRoad extends AbstractPlayerPiece implements
        VictoryPointItem
{
    private static final long serialVersionUID = -1851490325447009277L;
    private Route route;

    /**
     * @return the route
     */
    public Route getRoute()
    {
        return route;
    }

    @Override
    public int getVictoryPoints()
    {
        return 2;
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
