package soc.common.actions.gameAction.turnActions;

import java.util.ArrayList;
import java.util.List;

import soc.common.game.dices.StandardDice;



public class RollDice extends TurnAction
{
    private StandardDice dice;
    private List<Integer> looserPlayers = new ArrayList<Integer>();
    
    /**
     * @return the looserPlayers
     */
    public List<Integer> getLooserPlayers()
    {
        return looserPlayers;
    }

    /**
     * @return the dice
     */
    public StandardDice getDice()
    {
        return dice;
    }

    /**
     * @param dice the dice to set
     */
    public RollDice setDice(StandardDice dice)
    {
        this.dice = dice;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

}
