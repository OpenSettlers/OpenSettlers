package soc.common.server.actions;

import com.google.gwt.user.client.Random;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.game.Game;
import soc.common.game.dices.StandardDice;

public class ServerRollDice implements IServerAction
{
    RollDice rollDice;
    Game game;
    Random random;
    
    public ServerRollDice(RollDice rollDice, Game game, Random random)
    {
        super();
        this.rollDice = rollDice;
        this.game = game;
        this.random = random;
    }

    @Override
    public void execute()
    {
        StandardDice dice = new StandardDice();
        dice.roll(random);
        rollDice.setDice(dice);
    }

    @Override
    public GameAction getAction()
    {
        return rollDice;
    }

    @Override
    public GameAction getOpponentAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
