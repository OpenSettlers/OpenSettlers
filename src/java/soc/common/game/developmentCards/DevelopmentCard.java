package soc.common.game.developmentCards;

import java.io.Serializable;

import soc.common.board.resources.Ore;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Wheat;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.utils.ClassUtils;

public class DevelopmentCard implements Serializable
{
    private static final long serialVersionUID = 3192052784726040369L;
    protected String invalidMessage;
    protected String message = "No message implemented yet for Devcard"
            + toString();
    protected int turnBought = 0;
    private int id = 0;
    private boolean isPlayable = false;
    private static ResourceList cost = new ResourceList();

    static
    {
        cost.add(new Wheat());
        cost.add(new Ore());
        cost.add(new Sheep());
    }

    public boolean isLimitOnePerTurn()
    {
        return true;
    }

    /**
     * @return the hasSummoningSickness
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

    public void play(Game game, GamePlayer player)
    {
        isPlayable = false;
        player.getPlayedDevelopmentCards().add(this);
        player.getDevelopmentCards().remove(this);
    }

    public boolean isValid(Game game)
    {
        return true;
    }

    /*
     * Default is not to keep the DevelopmentCard in stock
     */
    public boolean keepInStock()
    {
        return false;
    }

    /*
     * Returns true if player is allowed to play this card in given TurnPhase
     */
    public boolean isAllowed(TurnPhase turnPhase)
    {
        throw new RuntimeException();
    }

    /*
     * Returns true if player is allowed to play this card in given GamePhase
     */
    public boolean isAllowed(GamePhase turnPhase)
    {
        throw new RuntimeException();
    }

    public String getInvalidMessage()
    {
        return invalidMessage;
    }

    public void setInvalidMessage(String invalidMessage)
    {
        this.invalidMessage = invalidMessage;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public int getTurnBought()
    {
        return turnBought;
    }

    public void setTurnBought(int turnBought)
    {
        this.turnBought = turnBought;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public boolean isPlayable()
    {
        return isPlayable;
    }

    public void setPlayable(boolean isPlayable)
    {
        this.isPlayable = isPlayable;
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
     * @see java.lang.Object#hashCode()
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
        DevelopmentCard other = (DevelopmentCard) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public String getName()
    {
        return ClassUtils.getSimpleClassName(this.getClass().getName());
    }

}
