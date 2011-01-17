package soc.common.server.actions;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.common.game.player.GamePlayer;
import soc.common.server.GameServer;

public class ServerRobPlayer implements ServerAction
{
    private RobPlayer robPlayer;
    private GameServer gameServer;

    public ServerRobPlayer(RobPlayer robPlayer, GameServer gameServer)
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
