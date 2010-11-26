package soc.common.game.developmentCards;

import soc.common.board.resources.*;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.Player;

public class Monopoly extends DevelopmentCard
{
    private Resource resource;

    /* Valid when having non-null resource and resource is of a basic type
     * @see soc.common.game.developmentCards.DevelopmentCard#isValid(soc.common.game.Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!(super.isValid(game)))
            return false;
        
        if (resource == null)
            return false;
        
        // Allow only one of the 5 basic resources
        if (!(resource instanceof Timber) &&
            !(resource instanceof Wheat) &&
            !(resource instanceof Ore) &&
            !(resource instanceof Clay) &&
            !(resource instanceof Sheep))
            return false;
        
        return true;
    }

    /* Steals all resources from opponents with given resource type
     * @see soc.common.game.developmentCards.DevelopmentCard#play(soc.common.game.Game, soc.common.game.Player)
     */
    @Override
    public void play(Game game, Player player)
    {
        StringBuilder msg = new StringBuilder();
        // TODO: fix using external library
        //msg.append(String.format("%s stole ", player.getName()));

        for (Player opponent : game.getPlayers())
        {
            //steal only form opponents
            if (!opponent.equals(player))
            {
                // Get the list of resources to steal 
                ResourceList opponentResources = opponent.getResources().ofType(resource); 
                
                // steal only if there are resources to steal anyways
                if (opponentResources.size() > 0)
                {
                    // add resources to the development card owner
                    player.getResources().addAll(opponentResources);

                    // TODO: fix using external library
                    //msg.append(String.format("%s %s from %s, ",
                    //    opponentResources.size(), resource.toString(), player.getName()));

                    // remove resources at victims
                    opponent.removeResources(opponentResources);
                }
            }
        }

        // remove the trailing ","
        message = msg.toString().substring(0, msg.toString().length() - 2);

        super.play(game, player);
    }

}
