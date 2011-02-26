package soc.common.game.developmentCards.standard;

import soc.common.actions.gameAction.standard.PlaceRobber;
import soc.common.actions.gameAction.standard.RobPlayer;
import soc.common.game.Game;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidgetFactory;

/*
 * Represents a soldier from the standard rules development card set.
 */
public class Soldier extends DevelopmentCard
{
    /**
     * 
     */
    private static final long serialVersionUID = 3097762985301413120L;

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
        // Update the army
        player.getArmy().addSoldier(this);

        game.switchLargestArmyIfNeeded(player);

        // Make sure next thing the player does is moving the robber...
        game.getActionsQueue().enqueue(new PlaceRobber().setPlayer(player),
                true);

        // ... and after that, rob a player
        game.getActionsQueue().enqueue(new RobPlayer().setPlayer(player), true);

        message = player.getUser().getName() + " played a soldier";

        super.play(game, player);
    }

    @Override
    public DevelopmentCardWidget createPlayCardWidget(
            DevelopmentCardWidgetFactory factory)
    {
        return factory.createSoldierWidget(this);
    }
}
