package soc.common.game.dices;

import soc.common.annotations.SeaFarers;

import com.google.gwt.user.client.Random;

@SeaFarers
public class VolcanoDice implements IDice
{
    private int dice = 0;

    /**
     * @return the dice
     */
    public int getDice()
    {
        return dice;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.dices.Dice#roll(java.util.Random)
     */
    @Override
    public void roll(Random random)
    {
        dice = (int) (Random.nextDouble() * 6);
    }

    /**
     * @param dice
     *            the dice to set
     */
    public VolcanoDice setDice(int dice)
    {
        this.dice = dice;

        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

}
