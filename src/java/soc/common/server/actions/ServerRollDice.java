package soc.common.server.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.RollDice;
import soc.common.game.dices.StandardDice;
import soc.common.server.GameServer;

public class ServerRollDice implements ServerAction
{
    RollDice rollDice;
    GameServer gameServer;

    public ServerRollDice(RollDice rollDice, GameServer gameServer)
    {
        super();
        this.rollDice = rollDice;
        this.gameServer = gameServer;
    }

    @Override
    public void execute()
    {
        StandardDice dice = new StandardDice();
        dice.roll(gameServer.getRandom());
        rollDice.setDice(dice);

        gameServer.getGame().performAction(rollDice);
    }

    @Override
    public GameAction getAction()
    {
        return rollDice;
    }

    @Override
    public GameAction getOpponentAction()
    {
        return rollDice;
    }
}
