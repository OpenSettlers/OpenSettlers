package soc.common.server.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.HostStartsGame;
import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.server.GameServer;

public class ServerActionFactory implements GameServerActionFactory
{
    public ServerAction createServerAction(GameAction action,
            GameServer gameServer)
    {
        if (action instanceof HostStartsGame)
        {
            return new ServerStartGame((HostStartsGame) action, gameServer);
        }

        if (action instanceof RobPlayer)
        {
            return new ServerRobPlayer((RobPlayer) action, gameServer);
        }

        if (action instanceof BuyDevelopmentCard)
        {
            return new ServerBuyDevelopmentCard((BuyDevelopmentCard) action,
                    gameServer);
        }

        if (action instanceof RollDice)
        {
            return new ServerRollDice((RollDice) action, gameServer);
        }

        // No associated server action found, return null
        return new DefaultAction(action, gameServer);
    }

}
