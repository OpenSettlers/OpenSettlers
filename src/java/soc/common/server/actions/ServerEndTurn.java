package soc.common.server.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.EndTurn;
import soc.common.server.GameServer;

public class ServerEndTurn implements ServerAction
{
    private GameServer gameServer;
    private EndTurn endTurn;

    public ServerEndTurn(EndTurn endTurn, GameServer gameServer)
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