package org.soc.common.server.gameActions;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.turns.EndTurn;
import org.soc.common.server.GameServer;

public class ServerEndTurn implements ServerAction
{
    private GameServer gameServer;
    private EndTurn endTurn;

    public ServerEndTurn(GameServer gameServer, EndTurn endTurn)
    {
        super();
        this.gameServer = gameServer;
        this.endTurn = endTurn;
    }

    @Override
    public void execute()
    {
        if (endTurn.getPlayer().getBot() != null)
            gameServer.setBotTurnHandled(true);

        gameServer.getGame().performAction(endTurn);
    }

    @Override
    public GameAction getAction()
    {
        return endTurn;
    }

    @Override
    public GameAction getOpponentAction()
    {
        return null;
    }
}