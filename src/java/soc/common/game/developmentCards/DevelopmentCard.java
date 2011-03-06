package soc.common.game.developmentCards;

import java.io.Serializable;

import soc.common.game.Game;
import soc.common.game.phases.GamePhase;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.views.meta.HasMeta;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidgetFactory;

public interface DevelopmentCard extends HasMeta, Serializable
{

    public boolean isLimitOnePerTurn();

    /** @return the hasSummoningSickness */
    public boolean isHasSummoningSickness();

    public void play(Game game, GamePlayer player);

    public boolean isValid(Game game);

    /*
     * Default is not to keep the DevelopmentCard in stock
     */
    public boolean keepInStock();

    /*
     * Returns true if player is allowed to play this card in given TurnPhase
     */
    public boolean isAllowed(TurnPhase turnPhase);

    /*
     * Returns true if player is allowed to play this card in given GamePhase
     */
    public boolean isAllowed(GamePhase turnPhase);

    public String getInvalidMessage();

    public void setInvalidMessage(String invalidMessage);

    public String getMessage();

    public void setMessage(String message);

    public int getTurnBought();

    public void setTurnBought(int turnBought);

    public int getId();

    public void setId(int id);

    public boolean isPlayable();

    public void setPlayable(boolean isPlayable);

    public int hashCode();

    public String getName();

    public DevelopmentCardWidget createPlayCardWidget(
                    DevelopmentCardWidgetFactory factory);

}