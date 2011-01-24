package soc.common.board.routing;

import soc.common.board.pieces.abstractPieces.PlayerPiece;
import soc.common.game.player.GamePlayer;

public interface GraphElement
{
    /*
     * Returns the player owning the element
     */
    public GamePlayer getPlayer();

    /*
     * Returns the playerpiece residing on the element
     */
    public PlayerPiece getPiece();

    public GraphElement setPlayerPiece(PlayerPiece piece);
}
