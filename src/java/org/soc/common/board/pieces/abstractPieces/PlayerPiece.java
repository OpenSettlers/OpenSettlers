package org.soc.common.board.pieces.abstractPieces;

import org.soc.common.board.resources.ResourceList;
import org.soc.common.game.player.GamePlayer;

public interface PlayerPiece extends Piece
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