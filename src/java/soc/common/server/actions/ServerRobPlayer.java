package soc.common.server.actions;

import com.google.gwt.user.client.Random;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.common.game.Game;
import soc.common.game.Player;

public class ServerRobPlayer implements IServerAction
{
    public RobPlayer robPlayer;
    public Game game;
    public Random random;
    
    public ServerRobPlayer(RobPlayer robPlayer, Game game, Random random)
    {
        super();
        this.robPlayer = robPlayer;
        this.game = game;
        this.random = random;
    }

    @Override
    public void execute()
    {
        Player victim = game.getPlayerByID(robPlayer.getVictimID());
        robPlayer.setStolenResource(victim.getResources().getRandom(random));
    }

    @Override
    public GameAction getAction()
    {
        return robPlayer;
    }

}
