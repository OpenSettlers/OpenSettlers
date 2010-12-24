package soc.common.board.routing;

import soc.common.board.pieces.PlayerPiece;
import soc.common.game.Player;

public interface IGraphElement
{
    /*
     * Returns the player owning the element
     */
    public Player getPlayer();
    
    /*
     * Returns the playerpiece residing on the element
     */
    public PlayerPiece getPiece();
    public IGraphElement setPlayerPiece(PlayerPiece piece);
}
