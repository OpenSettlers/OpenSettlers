package soc.common.server;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.MessageFromServer;
import soc.common.game.Game;
import soc.common.game.logs.QueuedAction;
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

    public AbstractGameServer()
    {
        super();

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
     * (non-Javadoc)
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
            QueuedAction expectedAction = null;
            if (!action.isValid(game))
            {
                callback.receive((GameAction) new MessageFromServer()
                        .setServerMessage(
                                "Invalid action! \r\n Reason: "
                                        + action.getInvalidMessage())
                        .setSender(0));
                return;
            }
            if (game.getActionsQueue().size() > 0)
            {
                expectedAction = game.getActionsQueue().findExpected(action,
                        game);
                if (expectedAction == null)
                {
                    notifyUnexpected(action);
                    return;
                }
            }

            // Get associated server side action if present
            ServerAction serverAction = serverActionFactory.createServerAction(
                    action, this);

            serverAction.execute();

            // send the action to all the players
            callback.receive(serverAction.getAction());

            // Check if the action enqueued a server action, if so: execute it
            // right away
            GameAction possibleNextServerAction = game.getActionsQueue()
                    .peekAction();
            if (possibleNextServerAction != null
                    && possibleNextServerAction.isServer())
            {
                sendAction(possibleNextServerAction);
            }
        }
    }

    private void notifyUnexpected(GameAction action)
    {
        // Grab the expected action
        GameAction expected = game.getActionsQueue().peekAction();

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
