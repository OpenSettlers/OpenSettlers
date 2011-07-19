package org.soc.common.game.developmentCards;

import java.io.Serializable;

import org.soc.common.game.Game;
import org.soc.common.game.phases.GamePhase;
import org.soc.common.game.phases.turnPhase.TurnPhase;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.meta.HasMeta;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidgetFactory;


import com.google.gwt.event.shared.HandlerRegistration;

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

    public String getMessage();

    /*
     * Gets the number of the turn this development card was bought
     */
    public int getTurnBought();

    /*
     * Sets the turn ID (==turn number) of the turn this development card was bought
     */
    public void setTurnBought(int turnBought);

    public int getId();

    public void setId(int id);

    /*
     * Returns true when this development card can be played this turn
     */
    public boolean isPlayable();

    public void setPlayable(boolean isPlayable);

    public int hashCode();

    public String getName();

    public DevelopmentCardWidget createPlayCardWidget(
                    DevelopmentCardWidgetFactory factory);
    public HandlerRegistration addPlayableChangedEventHandler(
                    PlayableChangedEventHandler handler);

}