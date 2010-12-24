package soc.common.server.actions;

import com.google.gwt.user.client.Random;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.common.game.Game;
import soc.common.game.Player;

public class ServerRobPlayer implements ServerAction
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
    public AbstractGameAction getAction()
    {
        return robPlayer;
    }

    @Override
    public AbstractGameAction getOpponentAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
