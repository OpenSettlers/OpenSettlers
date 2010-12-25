package soc.common.board.routing;

import org.jgrapht.GraphPath;

import soc.common.game.Player;

public interface Route extends GraphPath<GraphPoint, GraphSide>
{
    public Player getPlayer();
}
