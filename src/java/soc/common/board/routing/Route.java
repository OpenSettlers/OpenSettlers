package soc.common.board.routing;

import org.jgrapht.GraphPath;

import soc.common.game.Player;

public interface Route extends GraphPath<IGraphPoint, IGraphSide>
{
    public Player getPlayer();
}
