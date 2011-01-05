package soc.common.actions.gameAction;

import java.util.Date;

import soc.common.actions.AbstractAction;
import soc.common.game.Game;
import soc.common.game.GamePlayer;
import soc.common.game.GamePlayerImpl;
import soc.common.game.Turn;
import soc.common.game.statuses.GameStatus;
import soc.common.server.data.ServerUser;
import soc.common.utils.ClassUtils;

/*
 * A GameAction performed in a game
 */
public abstract class AbstractGameAction extends AbstractAction implements
        GameAction
{
    private static final long serialVersionUID = -2161422971683389585L;

    protected int sender;
    protected transient GamePlayer player;
    protected transient String invalidMessage;
    protected transient String toDoMessage;
    protected transient String message = "No message implemented yet";
    protected transient Turn turnExecuted;

    /*
     * Should be omitted at hashCode calculation, since values differ at server
     * and at client
     */
    protected Date dateTimeExecuted;

    /**
     * @return DateTime when this action is performed
     */
    @Override
    public Date getDateTimeExecuted()
    {
        return dateTimeExecuted;
    }

    /**
     * @return ID of the player initiating this action ID of 0 means the server
     *         initiated this action
     */
    public int getSender()
    {
        return sender;
    }

    /**
     * @param sender
     *            the sender to set
     */
    public AbstractGameAction setSender(int sender)
    {
        this.sender = sender;

        return this;
    }

    /**
     * @return Message explaining why this action is in invalid state
     */
    public String getInvalidMessage()
    {
        return invalidMessage;
    }

    /**
     * @return Message set after this action is performed
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @return the player
     */
    public GamePlayer getPlayer()
    {
        if (sender == 0 && player == null)
        {
            player = new GamePlayerImpl().setUser(new ServerUser());
        }
        return player;
    }

    /**
     * @param player
     *            the player to set
     */
    public GameAction setPlayer(GamePlayer player)
    {
        this.player = player;
        this.sender = player.getUser().getId();

        return this;
    }

    /*
     * Subclasses should call this method after they have performed their
     * specific implementation (at the end of the method)
     */
    public void perform(Game game)
    {
        // Set the dateTime when this action is performed
        dateTimeExecuted = new Date();

        // Add this action to the gamelog
        game.getGameLog().addAction(this);
    }

    /*
     * Returns true when this action is allowed Since we're deserializing raw
     * send data, we want to check it
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!isAllowed(game.getCurrentPhase()))
        {
            invalidMessage = getName() + " is not allowed during "
                    + game.getCurrentPhase().getName();
            return false;
        }

        return true;
    }

    // TODO: implement in subclasses and return i18n constants
    public String getName()
    {
        return ClassUtils.getSimpleClassName(this.getClass().getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#isAllowed(soc.common.game.statuses
     * .IGameStatus)
     */
    @Override
    public boolean isAllowed(GameStatus gameStatus)
    {
        if (gameStatus.isGameBlocking())
        {
            return false;
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.gameAction.GameAction#isServer()
     */
    @Override
    public boolean isServer()
    {
        return sender == 0;
    }

}
