package soc.common.server;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.MessageFromServer;
import soc.common.game.Game;
import soc.common.server.actions.IGameServerActionFactory;
import soc.common.server.actions.ServerAction;
import soc.common.server.random.Random;

/*
 * Abstracted implementation of a gameserver. Should be able to be used locally 
 * as hotseat implementation, as well as a thread synchronized remote server deployment.
 */
public abstract class AbstractGameServer implements GameServer
{
    protected IGameServerCallback callback;
    protected Game game;
    protected IGameServerActionFactory serverActionFactory;
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
    public void sendAction(AbstractGameAction action)
    {
        if (action != null)
        {
            if (!action.isValid(game))
            {
                callback.receive(new MessageFromServer().setServerMessage(
                        "Invalid action! \r\n Reason: "
                                + action.getInvalidMessage()).setSender(0));
                return;
            }
            if (game.getActionsQueue().size() > 0)
            {
                GameAction expectedAction = game.getActionsQueue()
                        .findExpected(action, game);
                if (expectedAction != null)
                {
                    game.getActionsQueue().dequeue(expectedAction);
                }
                else
                {
                    // Grab the expected action
                    GameAction expected = game.getActionsQueue().peekAction();

                    // Notify we did not expect current action
                    callback.receive(new MessageFromServer().setServerMessage(
                            "Invalid action! \r\n Reason: Expected "
                                    + expected.toString()
                                    + " from player "
                                    + game.getPlayerByID(expected.getSender())
                                            .getUser().getName()
                                    + ", but instead got "
                                    + action.toString()
                                    + " from player "
                                    + game.getPlayerByID(action.getSender())
                                            .getUser().getName()).setSender(0));
                    return;
                }
            }

            // Get associated serverside action if present
            ServerAction serverAction = serverActionFactory.createServerAction(
                    action, this);

            serverAction.execute();

            // send the action to all the players
            callback.receive(serverAction);
        }
    }
}
