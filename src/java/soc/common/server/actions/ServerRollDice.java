package soc.common.server.actions;

import com.google.gwt.user.client.Random;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.game.Game;
import soc.common.game.dices.StandardDice;

public class ServerRollDice implements ServerAction
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
    public AbstractGameAction getAction()
    {
        return rollDice;
    }

    @Override
    public AbstractGameAction getOpponentAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
