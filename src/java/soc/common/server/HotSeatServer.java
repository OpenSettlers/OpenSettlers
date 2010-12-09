package soc.common.server;

import com.google.gwt.user.client.Random;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.MessageFromServer;
import soc.common.game.Game;
import soc.common.server.actions.IServerAction;
import soc.common.server.actions.ServerActionFactory;

public class HotSeatServer implements IGameServer
{
    private IGameServerCallback callback;
    private Game game;
    private IGameServerActionFactory serverActionFactory;
    private Random random;

    public HotSeatServer(IGameServerCallback callback, Game game)
    {
        this.callback=callback;
        this.game=game;
        serverActionFactory = new ServerActionFactory();
    }
    
    @Override
    public JoinResult join(UserCredentials credentials)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void leave()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendAction(GameAction action)
    {
        if (action != null)
        {
            if (!action.isValid())
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
                    GameAction expected = game.getActionsQueue().peek();

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
            IServerAction serverAction =  serverActionFactory.getServerAction(action, game, random);
            
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
