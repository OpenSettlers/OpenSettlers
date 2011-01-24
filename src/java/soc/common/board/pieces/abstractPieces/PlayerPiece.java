package soc.common.board.pieces.abstractPieces;

import soc.common.board.resources.ResourceList;
import soc.common.game.player.GamePlayer;

public interface PlayerPiece
{
    public GamePlayer getPlayer();

    public PlayerPiece setPlayer(GamePlayer player);

    public ResourceList getCost();

    public boolean canPay(GamePlayer player);

    public String getName();

    public boolean isStockPiece();

    public void addToPlayer(GamePlayer player);

    public void removeFromPlayer(GamePlayer player);

    public boolean affectsRoad();
}