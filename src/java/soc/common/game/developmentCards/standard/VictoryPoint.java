package soc.common.game.developmentCards.standard;

import soc.common.game.Game;
import soc.common.game.VictoryPointItem;
import soc.common.game.developmentCards.AbstractDevelopmentCard;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidgetFactory;
import soc.gwt.client.images.Resources;

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
     * @see soc.common.game.developmentCards.DevelopmentCard#keepInStock()
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
     * soc.common.game.developmentCards.DevelopmentCard#isValid(soc.common.game
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
     * soc.common.game.developmentCards.DevelopmentCard#isHasSummoningSickness()
     */
    @Override
    public boolean isHasSummoningSickness()
    {
        return false;
    }

    /*
     * VictoryPoint cards can be played without limits
     * 
     * @see soc.common.game.developmentCards.DevelopmentCard#isLimitOnePerTurn()
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
     * soc.common.game.developmentCards.DevelopmentCard#play(soc.common.game
     * .Game, soc.common.game.Player)
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
     * @see soc.common.game.developmentCards.AbstractDevelopmentCard#isPlayable()
     */
    @Override
    public boolean isPlayable()
    {
        return true;
    }

    /*
     * Ignored, VictoryPoint card is always playable
     * 
     * @see soc.common.game.developmentCards.AbstractDevelopmentCard#setPlayable(boolean)
     */
    @Override
    public void setPlayable(boolean isPlayable)
    {
    }
}