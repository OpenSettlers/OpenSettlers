package org.soc.common.game.developmentCards;

import java.io.Serializable;

import org.soc.common.board.resources.Ore;
import org.soc.common.board.resources.ResourceList;
import org.soc.common.board.resources.Sheep;
import org.soc.common.board.resources.Wheat;
import org.soc.common.game.Game;
import org.soc.common.game.phases.GamePhase;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.utils.ClassUtils;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidgetFactory;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public abstract class AbstractDevelopmentCard implements Serializable,
                DevelopmentCard
{
    private static final long serialVersionUID = 3192052784726040369L;
    protected String invalidMessage;
    protected String message = "No message implemented yet for Devcard"
                    + toString();
    protected int turnBought = 0;
    private int id = 0;
    private boolean isPlayable = false;
    private static ResourceList cost = new ResourceList();
    private transient EventBus eventBus = new SimpleEventBus();

    static
    {
        cost.add(new Wheat());
        cost.add(new Ore());
        cost.add(new Sheep());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.developmentCards.DevelopmentCard#isLimitOnePerTurn()
     */
    public boolean isLimitOnePerTurn()
    {
        return true;
    }

    /*
     * Returns true when the given phase is in PlayTurns
     * 
     * @see
     * org.soc.common.game.developmentCards.DevelopmentCard#isAllowed(org.soc.common
     * .game.phases.GamePhase)
     */
    @Override
    public boolean isAllowed(GamePhase turnPhase)
    {
        return turnPhase.isPlayTurns();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.game.developmentCards.DevelopmentCard#isHasSummoningSickness()
     */
    public boolean isHasSummoningSickness()
    {
        return true;
    }

    public static boolean canPay(GamePlayer player)
    {
        // TODO: add diamonds support
        // First, create a copy so we can safely remove resources from it
        ResourceList copy = player.getResources().copy();

        // Pay resources player can simply pay for
        copy.subtractResources(getCost());

        // Calculate amount of gold we need
        int neededGold =
        // amount of resources this piece needs, minus...
        getCost().size() -
        // the resources the player can simply pay for
                        (player.getResources().size() - copy.size());

        // Player can pay given piece if he can trade exactly or more gold as
        // needed
        return player.getPorts().amountGold(copy) >= neededGold;
    }

    public static ResourceList getCost()
    {
        return cost;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.game.developmentCards.DevelopmentCard#play(org.soc.common.game
     * .Game, org.soc.common.game.player.GamePlayer)
     */
    public void play(Game game, GamePlayer player)
    {
        isPlayable = false;
        player.getPlayedDevelopmentCards().add(this);
        player.getDevelopmentCards().remove(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.game.developmentCards.DevelopmentCard#isValid(org.soc.common.game
     * .Game)
     */
    public boolean isValid(Game game)
    {
        return true;
    }

    /*
     * Default is not to keep the DevelopmentCard in stock
     */
    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.developmentCards.DevelopmentCard#keepInStock()
     */
    public boolean keepInStock()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.developmentCards.DevelopmentCard#getInvalidMessage()
     */
    public String getInvalidMessage()
    {
        return invalidMessage;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.developmentCards.DevelopmentCard#getMessage()
     */
    public String getMessage()
    {
        return message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.developmentCards.DevelopmentCard#getTurnBought()
     */
    public int getTurnBought()
    {
        return turnBought;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.developmentCards.DevelopmentCard#setTurnBought(int)
     */
    public void setTurnBought(int turnBought)
    {
        this.turnBought = turnBought;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.developmentCards.DevelopmentCard#getId()
     */
    public int getId()
    {
        return id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.developmentCards.DevelopmentCard#setId(int)
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.developmentCards.DevelopmentCard#isPlayable()
     */
    public boolean isPlayable()
    {
        return isPlayable;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.game.developmentCards.DevelopmentCard#setPlayable(boolean)
     */
    public void setPlayable(boolean isPlayable)
    {
        this.isPlayable = isPlayable;
        eventBus.fireEvent(new PlayableChangedEvent(isPlayable));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return ClassUtils.getSimpleClassName(this.getClass().getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.developmentCards.DevelopmentCard#hashCode()
     */
    @Override
    public int hashCode()
    {
        return id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractDevelopmentCard other = (AbstractDevelopmentCard) obj;
        if (id != other.id)
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.developmentCards.DevelopmentCard#getName()
     */
    public String getName()
    {
        return ClassUtils.getSimpleClassName(this.getClass().getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.game.developmentCards.DevelopmentCard#createPlayCardWidget
     * (org.soc.
     * common.views.widgetsInterface.developmentCards.DevelopmentCardWidgetFactory
     * )
     */
    public abstract DevelopmentCardWidget createPlayCardWidget(
                    DevelopmentCardWidgetFactory factory);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.game.developmentCards.DevelopmentCard#addPlayableChangedEventHandler(org.soc.common
     * .game.developmentCards.PlayableChangedEventHandler)
     */
    @Override
    public HandlerRegistration addPlayableChangedEventHandler(
                    PlayableChangedEventHandler handler)
    {
        return eventBus.addHandler(PlayableChangedEvent.TYPE, handler);
    }

}
