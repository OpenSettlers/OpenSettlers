package soc.common.server.actions;

import com.google.gwt.user.client.Random;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.HostStartsGame;
import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.common.game.Game;
import soc.common.server.IGameServerActionFactory;

public class ServerActionFactory implements IGameServerActionFactory
{
    public IServerAction getServerAction(GameAction action, Game game, Random random)
    {
        if (action instanceof HostStartsGame)
        {
            return new ServerStartGame((HostStartsGame)action, random);
        }
        
        if (action instanceof RobPlayer)
        {
            return new ServerRobPlayer((RobPlayer)action, game, random);
        }
        
        if (action instanceof BuyDevelopmentCard)
        {
            return new ServerBuyDevelopmentCard((BuyDevelopmentCard)action, game);
        }
        
        // No associated server action found, return null
        return null;
    }
}
