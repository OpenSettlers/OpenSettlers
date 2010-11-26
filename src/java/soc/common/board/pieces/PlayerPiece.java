package soc.common.board.pieces;

import soc.common.board.Board;
import soc.common.board.resources.ResourceList;
import soc.common.game.Player;

public class PlayerPiece extends Piece
{
    public ResourceList getCost()
    {
        throw new RuntimeException();
    }
    
    public boolean canPay(Player player)
    {
        // First, create a copy so we can safely remove resources from it
        ResourceList copy = player.getResources().copy();
        
        // Pay resources player can simply pay for
        copy.subtractResources(getCost());
        
        // Calculate amount of gold we need
        int neededGold =
            // amount of resources this piece needs, minus
            getCost().size() - 
                // the resources the player can simply pay for 
                (player.getResources().size() - copy.size());
        
        // Player can pay given piece if he can trade exactly or more gold as needed
        return player.amountGold(copy) <= neededGold;    }
    
    public boolean canBuild(Board board, Player player)
    {
        throw new RuntimeException();
    }
}
