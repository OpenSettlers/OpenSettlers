package soc.common.game.developmentCards;

import soc.common.game.Game;
import soc.common.game.phases.GamePhase;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidgetFactory;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.shared.HandlerRegistration;

/*
 * Placeholder for a development card bought by an opponent, as seen from
 * the perspective of the player. A ServerBuyDevelopmentCard will replace the
 * development card of the corresponding BuyDevelopmentCard to an instance of
 * this type.
 */
public class DummyDevelopmentCard implements DevelopmentCard
{
    private static final long serialVersionUID = 1304353703764982089L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons()
                        .developmentCardBack16(), Resources.icons()
                        .developmentCardBack32(), Resources.icons()
                        .developmentCardBack48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "RoadBuilding";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().dummyDevelopmentCard();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().dummyDevelopmentCardDescription();
        }
    };

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    private int id;
    private int turnBought;

    @Override
    public DevelopmentCardWidget createPlayCardWidget(
                    DevelopmentCardWidgetFactory factory)
    {
        return null;
    }

    @Override
    public int getId()
    {
        return id;
    }

    @Override
    public String getInvalidMessage()
    {
        return null;
    }

    @Override
    public String getMessage()
    {
        return "Dummy devcard";
    }

    @Override
    public String getName()
    {
        return "Dummy development card";
    }

    @Override
    public int getTurnBought()
    {
        return 0;
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return false;
    }

    @Override
    public boolean isAllowed(GamePhase turnPhase)
    {
        return false;
    }

    @Override
    public boolean isHasSummoningSickness()
    {
        return true;
    }

    @Override
    public boolean isLimitOnePerTurn()
    {
        return true;
    }

    @Override
    public boolean isPlayable()
    {
        return false;
    }

    @Override
    public boolean isValid(Game game)
    {
        return false;
    }

    @Override
    public boolean keepInStock()
    {
        return false;
    }

    @Override
    public void play(Game game, GamePlayer player)
    {
    }

    @Override
    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public void setPlayable(boolean isPlayable)
    {
    }

    @Override
    public void setTurnBought(int turnBought)
    {
        this.turnBought = turnBought;
    }

    @Override
    public HandlerRegistration addPlayableChangedEventHandler(
                    PlayableChangedEventHandler handler)
    {
        return null;
    }

}
