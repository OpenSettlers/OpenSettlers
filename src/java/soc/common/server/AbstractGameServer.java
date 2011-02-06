package soc.common.server;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.MessageFromServer;
import soc.common.bots.BotPrincipal;
import soc.common.game.Game;
import soc.common.game.logs.ActionsQueue;
import soc.common.server.actions.GameServerActionFactory;
import soc.common.server.actions.ServerAction;
import soc.common.server.random.Random;

/*
 * Abstracted implementation of a gameserver. Should be able to be used locally 
 * as hotseat implementation, as well as a thread synchronized remote server deployment.
 */
public abstract class AbstractGameServer implements GameServer
{
    protected GameServerCallback callback;
    protected Game game;
    protected GameServerActionFactory serverActionFactory;
    protected Random random;
    protected BotPrincipal botPrincipal;

    public AbstractGameServer()
    {
        serverActionFactory = createActionFactory();
    }

    /**
     * @return the random
     */
    public soc.common.server.random.Random getRandom()
    {
        return random;
    }

    @Override
    public Game getGame()
    {
        return game;
    }

    /*
     * Receives an action from a player
     * 
     * @see
     * soc.common.server.IGameServer#sendAction(soc.common.actions.gameAction
     * .GameAction)
     */
    @Override
    public void sendAction(GameAction action)
    {
        if (action != null)
        {
            GameAction expectedAction = null;
            if (!game.isAllowed(action))
            {
                notifyNotAllowed(action);
                return;
            }
            if (!action.isValid(game))
            {
                notifyInvalid(action);
                return;
            }
            if (game.getActionsQueue().size() > 0)
            {
                ActionsQueue ac = game.getActionsQueue();
                expectedAction = ac.findExpected(action);
                if (expectedAction == null)
                {
                    notifyUnexpected(action);
                    return;
                }
            }

            // Get associated server side action
            ServerAction serverAction = serverActionFactory.createServerAction(
                    action, this);

            serverAction.execute();

            // send the action to all the players
            callback.receive(serverAction.getAction());

            GameAction possibleNextServerAction = game.getActionsQueue().peek();

            // Check if the action enqueued a server action, if so: execute it
            // right away
            if (possibleNextServerAction != null
                    && possibleNextServerAction.isServer())
            {
                sendAction(possibleNextServerAction);
            }

            // If bots should perform actions, call the bot principal to let the
            // bots handle them
            if (possibleNextServerAction != null && game.hasBots())
            {
                botPrincipal.handleActionsQueue();
            }
        }
    }

    private void notifyNotAllowed(GameAction action)
    {
        callback.receive((GameAction) new MessageFromServer()
                .setServerMessage("Action not allowed!"));
    }

    private void notifyInvalid(GameAction action)
    {
        callback.receive((GameAction) new MessageFromServer().setServerMessage(
                "Invalid action! \r\n Reason: " + action.getInvalidMessage())
                .setSender(0));
    }

    private void notifyUnexpected(GameAction action)
    {
        // Grab the expected action
        GameAction expected = game.getActionsQueue().peek();

        // Notify we did not expect current action
        // TODO 200 fix message
        callback.receive((GameAction) new MessageFromServer().setServerMessage(
                "Invalid action! \r\n Reason: Expected "
                        + expected.toString()
                        + " from player "
                        + game.getPlayerByID(expected.getSender()).getUser()
                                .getName()
                        + ", but instead got "
                        + action.toString()
                        + " from player "
                        + game.getPlayerByID(action.getSender()).getUser()
                                .getName()).setSender(0));
    }
}
