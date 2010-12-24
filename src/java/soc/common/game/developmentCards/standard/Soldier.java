package soc.common.game.developmentCards.standard;

import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.logs.QueuedAction;

public class Soldier extends DevelopmentCard
{

    /* (non-Javadoc)
     * @see soc.common.game.developmentCards.DevelopmentCard#isValid(soc.common.game.Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game)) return false;

        return true;
    }

    /* (non-Javadoc)
     * @see soc.common.game.developmentCards.DevelopmentCard#play(soc.common.game.Game, soc.common.game.Player)
     */
    @Override
    public void play(Game game, Player player)
    {
        // Update largest army
        if (game.getGameRules().getEnableLargestArmy())
        {
            //TODO: update game LA
            //game.CalculateLargestArmy(player);
        }
        
        // Make sure next thing the player does is moving the robber
        game.getActionsQueue().enqueue(
            new QueuedAction()
                .setAction
                (
                    new PlaceRobber()
                        .setPlayer(player)
                )
                .setBlocking(true)
                .setOptional(false)
        );
        
        message = player.getName() + " played a soldier";

        super.play(game, player);
    }

}
