package soc.common.board.routing;

import java.io.Serializable;

import org.jgrapht.GraphPath;

import soc.common.game.player.GamePlayer;

public interface Route extends GraphPath<GraphPoint, GraphSide>, Serializable
{
    public GamePlayer getPlayer();

    public Route setPlayer(GamePlayer player);

    public int getLength();

    public boolean validate();
}
