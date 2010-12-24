package soc.common.server;

import com.google.gwt.user.client.Random;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.MessageFromServer;
import soc.common.game.Game;
import soc.common.server.actions.ServerAction;

/*
 * Abstracted implementation of a gameserver. Should be able to be used locally 
 * as hotseat implementation, as well as a thread synchronized remote server deployment.
 */
public abstract class AbstractGameServer implements IGameServer
{
    protected IGameServerCallback callback;
    protected Game game;
    protected IGameServerActionFactory serverActionFactory;
    protected Random random;
    
    /* (non-Javadoc)
     * @see soc.common.server.IGameServer#sendAction(soc.common.actions.gameAction.GameAction)
     */
    @Override
    public void sendAction(AbstractGameAction action)
    {
        if (action != null)
        {
            if (!action.isValid(game))
            {
                callback.receive
                (
                    new MessageFromServer()
                        .setServerMessage("Invalid action! \r\n Reason: "+ action.getInvalidMessage())
                        .setSender(0)
                );
                return;
            }
            if (game.getActionsQueue().size() > 0)
            {
                if (game.getActionsQueue().isExpected(action, game))
                {
                    game.getActionsQueue().dequeue();
                }
                else
                {
                    // Grab the expected action
                    GameAction expected = game.getActionsQueue().peekAction();

                    // Notify we did not expect current action
                    callback.receive
                    (
                        new MessageFromServer()
                        .setServerMessage("Invalid action! \r\n Reason: Expected " + expected.toString() +  
                            " from player " + game.getPlayerByID(expected.getSender()).getName() +
                            ", but instead got " + action.toString() +
                            " from player " + game.getPlayerByID(action.getSender()).getName()) 
                        .setSender(0)
                    );
                    return;
                }
            }
            
            // Get associated serverside action if present
            ServerAction serverAction =  serverActionFactory.getServerAction(action, game, random);
            
            // Execute it when associated server action is found
            if (serverAction != null)
            {
                serverAction.execute();
            }
            
            // perform the action on the game object
            action.perform(game);
            
            // send the action to all the players
            callback.receive(action);
        }
    }
    
}
