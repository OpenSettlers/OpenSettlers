package soc.common.game.developmentCards;

import soc.common.game.Game;
import soc.common.game.phases.GamePhase;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidgetFactory;

/*
 * Placeholder for a development card bought by an opponent, as seen from 
 * the perspective of the player. A ServerBuyDevelopmentCard will replace the
 * development card of the corresponding BuyDevelopmentCard to an instance of
 * this type.
 */
public class DummyDevelopmentCard implements DevelopmentCard
{
    private static final long serialVersionUID = 1304353703764982089L;
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
    public void setInvalidMessage(String invalidMessage)
    {
    }

    @Override
    public void setMessage(String message)
    {
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

}
