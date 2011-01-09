package soc.common.game.developmentCards.standard;

import java.util.List;

import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.board.pieces.LargestArmy;
import soc.common.game.Game;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.logs.QueuedAction;
import soc.common.game.player.GamePlayer;

public class Soldier extends DevelopmentCard
{

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.developmentCards.DevelopmentCard#isValid(soc.common.game
     * .Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game))
            return false;

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.developmentCards.DevelopmentCard#play(soc.common.game
     * .Game, soc.common.game.Player)
     */
    @Override
    public void play(Game game, GamePlayer player)
    {
        player.getPlayedDevelopmentCards().add(this);

        // Update largest army only when gamerules support it
        if (game.getGameRules().getLargestArmy() != null)
        {
            List<DevelopmentCard> playerSoldiers = player
                    .getPlayedDevelopmentCards().ofType(this);

            // Only check largest army update when player has more then 2
            if (playerSoldiers.size() > 2)
            {
                boolean hasMore = true;
                for (GamePlayer opponent : game.getPlayers())
                {
                    if (opponent.getPlayedDevelopmentCards().ofType(this)
                            .size() >= playerSoldiers.size())
                    {
                        hasMore = false;
                        break;
                    }
                }
                if (hasMore
                        && player.getVictoryPoints().ofType(
                                LargestArmy.LARGESTARMY).size() == 1)
                {
                    game.switchLargestArmy(player);
                }
            }
        }

        // Make sure next thing the player does is moving the robber
        game.getActionsQueue().enqueue(
                new QueuedAction().setAction(
                        new PlaceRobber().setPlayer(player)).setBlocking(true)
                        .setOptional(false));

        message = player.getUser().getName() + " played a soldier";

        super.play(game, player);
    }
}
