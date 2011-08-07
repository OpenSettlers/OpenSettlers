package org.soc.common.game.developmentCards.standard;

import org.soc.common.actions.gameAction.standard.PlaceRobber;
import org.soc.common.actions.gameAction.standard.RobPlayer;
import org.soc.common.game.Game;
import org.soc.common.game.developmentCards.AbstractDevelopmentCard;
import org.soc.common.game.phases.turnPhase.TurnPhase;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidgetFactory;
import org.soc.gwt.client.images.Resources;

/*
 * Represents a soldier from the standard rules development card set.
 */
public class Soldier extends AbstractDevelopmentCard
{
    private static final long serialVersionUID = 3097762985301413120L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().soldier16(),
                        Resources.icons().soldier32(), Resources.icons()
                                        .soldier48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Soldier";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().soldier();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().soldierDescription();
        }
    };

    @Override
    public Meta getMeta()
    {
        return meta;
    }
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.game.developmentCards.DevelopmentCard#isValid(org.soc.common.game
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
     * org.soc.common.game.developmentCards.DevelopmentCard#play(org.soc.common.game
     * .Game, org.soc.common.game.Player)
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

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase.isBeforeDiceRoll() || turnPhase.isBuilding();
    }
}
