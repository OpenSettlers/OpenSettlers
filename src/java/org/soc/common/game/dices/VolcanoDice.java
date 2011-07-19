package org.soc.common.game.dices;

import org.soc.common.annotations.SeaFarers;
import org.soc.common.server.randomization.Random;
import org.soc.common.views.widgetsInterface.actions.DiceWidget;
import org.soc.common.views.widgetsInterface.actions.DiceWidgetFactory;

/*
 * Represents a single 6-sided dice. Rolled when a volcano produces resources by
 * the player currently on turn. Each HexPoint matching the rolled dice number is
 * hit by the volcano. A volcano has 6 HexPoints, corresponding with the rolld number.
 * 
 * When an HexPoint is hit four things can happen. When no town or city is present,
 * nothing happens (1). When a town resides on the HexPoint, it will be blown up 
 * (removed from the board, put back into players' stock) (2). If a city is present,
 * a town is put back when the player has a town in stock (3). If a city is present 
 * and the player does not have a town in stock, the city is removed and no town is 
 * placed back on the city's HexPoint (4).
 */
@SeaFarers
public class VolcanoDice implements Dice
{
    private static final long serialVersionUID = -4243164420585542860L;
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
     * @see org.soc.common.game.dices.Dice#roll(java.util.Random)
     */
    @Override
    public void roll(Random random)
    {
        dice = random.nextInt(6, true);
    }

    /**
     * @param dice
     *            the dice to set
     */
    public VolcanoDice setDice(int dice)
    {
        this.dice = dice;

        return this;
    }

    @Override
    public DiceWidget createDiceWidget(DiceWidgetFactory factory)
    {
        return factory.createVolcanoDiceWidget();
    }

}
