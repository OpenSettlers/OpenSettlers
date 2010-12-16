package soc.common.game.developmentCards;

import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.Player;

public class YearOfPlenty extends DevelopmentCard 
{
    //actual picked resources by player
    private ResourceList goldPick = new ResourceList();
    
    /**
     * @return the two picked resources
     */
    public ResourceList getGoldPick()
    {
        return goldPick;
    }

    @Override
    public void play(Game game, Player player)
    {
        // TODO: fix mssage     
        //message = String.format("%s gained %s by playing a Year of Plenty card",
        //        player.getName(), goldPick.toString());
            
        // give player the resources
        player.getResources().swapResourcesFrom(goldPick, game.getBank());

        super.play(game, player);
    }
    
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game))
            return false;
        
        if (goldPick == null)
        {
            invalidMessage = "Resources cannot be null";
            return false;
        }
        
        if (goldPick.size() != 2)
        {
            invalidMessage = "Exactly 2 resources should be picked";
            return false;
        }
        
        if (!(game.getBank().hasAtLeast(goldPick)))
        {
            invalidMessage = "Bank does not have picked resources";
            return false;
        }
        
        return true;
    }
    
}
