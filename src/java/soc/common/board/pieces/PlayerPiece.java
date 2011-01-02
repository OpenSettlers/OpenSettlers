package soc.common.board.pieces;

import soc.common.board.resources.ResourceList;
import soc.common.game.GamePlayer;

public interface PlayerPiece
{

    /**
     * @return the player
     */
    public abstract GamePlayer getPlayer();

    /**
     * @param player
     *            the player to set
     */
    public abstract PlayerPiece setPlayer(GamePlayer player);

    public abstract ResourceList getCost();

    public abstract boolean canPay(GamePlayer player);

    public abstract String getName();

    public boolean isStockPiece();
}