package soc.common.server.actions;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.HostStartsGame;
import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.common.game.Game;
import soc.common.server.IGameServerActionFactory;

import com.google.gwt.user.client.Random;

public class ServerActionFactory implements IGameServerActionFactory
{
    public ServerAction getServerAction(AbstractGameAction action, Game game,
            Random random)
    {
        if (action instanceof HostStartsGame)
        {
            return new ServerStartGame((HostStartsGame) action, random);
        }

        if (action instanceof RobPlayer)
        {
            return new ServerRobPlayer((RobPlayer) action, game, random);
        }

        if (action instanceof BuyDevelopmentCard)
        {
            return new ServerBuyDevelopmentCard((BuyDevelopmentCard) action,
                    game);
        }

        // No associated server action found, return null
        return new DefaultAction(action);
    }
}
