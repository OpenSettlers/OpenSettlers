package soc.common.board.routing;

import java.util.List;

import org.jgrapht.Graph;

import soc.common.board.territories.Territory;
import soc.common.game.player.GamePlayer;

/*
 * Represents a route from Territory A to Territory B. 
 */
public class TradeRouteImpl implements TradeRoute
{
    private static final long serialVersionUID = 8219564988646779047L;

    @Override
    public Territory getDestinationTerritory()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Territory getFromTerritory()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getLength()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public GamePlayer getPlayer()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GraphSide> getEdgeList()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GraphPoint getEndVertex()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Graph<GraphPoint, GraphSide> getGraph()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GraphPoint getStartVertex()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getWeight()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getVictoryPoints()
    {
        return 1;
    }

    @Override
    public Route setPlayer(GamePlayer player)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean validate()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
