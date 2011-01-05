package soc.common.board.routing;

import org.jgrapht.GraphPath;

import soc.common.game.player.GamePlayer;

public interface Route extends GraphPath<GraphPoint, GraphSide>
{
    public GamePlayer getPlayer();

    public int getLength();
}
