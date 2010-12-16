package soc.common.board.pieces;

import soc.common.board.Board;
import soc.common.board.resources.ResourceList;
import soc.common.game.Player;
import soc.common.utils.ClassUtils;

public class PlayerPiece extends Piece
{
    protected Player player;
    
    /**
     * @return the player
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * @param player the player to set
     */
    public PlayerPiece setPlayer(Player player)
    {
        this.player = player;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

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
            // amount of resources this piece needs, minus...
            getCost().size() - 
                // the resources the player can simply pay for 
                (player.getResources().size() - copy.size());
        
        // Player can pay given piece if he can trade exactly or more gold as needed
        return player.amountGold(copy) <= neededGold;    
    }
    
    public boolean canBuild(Board board, Player player)
    {
        throw new RuntimeException();
    }
    
    public String getName()
    {
        return ClassUtils.getSimpleClassName(this.getClass().getName());
    }
}
