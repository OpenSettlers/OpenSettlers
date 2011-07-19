package org.soc.common.server.gameActions;

import org.soc.common.actions.gameAction.AbstractGameAction;
import org.soc.common.actions.gameAction.standard.RobPlayer;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.server.GameServer;

public class ServerRobPlayer implements ServerAction
{
    private RobPlayer robPlayer;
    private GameServer gameServer;

    public ServerRobPlayer(GameServer gameServer, RobPlayer robPlayer)
    {
        super();
        this.robPlayer = robPlayer;
        this.gameServer = gameServer;
    }

    @Override
    public void execute()
    {
        GamePlayer victim = gameServer.getGame().getPlayerByID(
                robPlayer.getVictimID());
        if (victim != null)
        {
            robPlayer.setStolenResource(victim.getResources().getRandom(
                    gameServer.getRandom()));
        }

        gameServer.getGame().performAction(robPlayer);
    }

    @Override
    public AbstractGameAction getAction()
    {
        return robPlayer;
    }

    @Override
    public AbstractGameAction getOpponentAction()
    {
        return robPlayer;
    }
}
