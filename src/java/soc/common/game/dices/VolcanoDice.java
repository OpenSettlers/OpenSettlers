package soc.common.game.dices;

import com.google.gwt.user.client.Random;

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

    /* (non-Javadoc)
     * @see soc.common.game.dices.Dice#roll(java.util.Random)
     */
    @Override
    public void roll(Random random)
    {
        dice = (int) (random.nextDouble() * 6);
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
