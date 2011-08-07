package org.soc.common.game.developmentCards.standard;

import org.soc.common.game.Game;
import org.soc.common.game.VictoryPointItem;
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
 * Standard ruleset VictoryPoint development card
 * Playable any time during a players' turn and not immediately played when
 * receiving the card
 */
public class VictoryPoint extends AbstractDevelopmentCard implements
                VictoryPointItem
{
    private static final long serialVersionUID = -7322456488887068608L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().victoryPoint16(),
                        Resources.icons().victoryPoint32(), Resources.icons()
                                        .victoryPoint48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "VictoryPoint";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().victoryPoint();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().victoryPointDescription();
        }
    };

    /*
     * A victoryPoint development card returns into stock after playing
     * 
     * @see org.soc.common.game.developmentCards.DevelopmentCard#keepInStock()
     */
    @Override
    public boolean keepInStock()
    {
        return true;
    }

    /*
     * A VictoryPoint development card is always valid
     * 
     * @see
     * org.soc.common.game.developmentCards.DevelopmentCard#isValid(org.soc.common.game
     * .Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        return true;
    }

    @Override
    public int getVictoryPoints()
    {
        return 1;
    }

    /*
     * VictoryPoint card can be played instantly
     * 
     * @see
     * org.soc.common.game.developmentCards.DevelopmentCard#isHasSummoningSickness()
     */
    @Override
    public boolean isHasSummoningSickness()
    {
        return false;
    }

    /*
     * VictoryPoint cards can be played without limits
     * 
     * @see org.soc.common.game.developmentCards.DevelopmentCard#isLimitOnePerTurn()
     */
    @Override
    public boolean isLimitOnePerTurn()
    {
        return false;
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
        player.getVictoryPoints().add(this);

        super.play(game, player);
    }

    @Override
    public DevelopmentCardWidget createPlayCardWidget(
                    DevelopmentCardWidgetFactory factory)
    {
        return factory.createVictoryPointWidget(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase.isBeforeDiceRoll() || turnPhase.isBuilding();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.developmentCards.AbstractDevelopmentCard#isPlayable()
     */
    @Override
    public boolean isPlayable()
    {
        return true;
    }

    /*
     * Ignored, VictoryPoint card is always playable
     * 
     * @see org.soc.common.game.developmentCards.AbstractDevelopmentCard#setPlayable(boolean)
     */
    @Override
    public void setPlayable(boolean isPlayable)
    {
    }
}