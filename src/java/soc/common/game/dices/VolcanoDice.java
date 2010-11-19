package soc.common.game.dices;

import soc.common.annotations.SeaFarers;

@SeaFarers
public class VolcanoDice extends Dice
{
    private int dice = 0;

    /**
     * @return the dice
     */
    public int getDice()
    {
        return dice;
    }

    /**
     * @param dice the dice to set
     */
    public VolcanoDice setDice(int dice)
    {
        this.dice = dice;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
    
    
}
