package soc.common.actions.gameAction.turnActions.standard;

import com.google.gwt.user.client.Random;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import soc.common.game.dices.Dice;
import soc.common.game.dices.StandardDice;
import soc.common.server.actions.IServerAction;

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

}
