package org.soc.common.game.dices;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.server.randomization.Random;
import org.soc.common.views.widgetsInterface.actions.DiceWidget;
import org.soc.common.views.widgetsInterface.actions.DiceWidgetFactory;


/*
 * Represents a deck of 36 cards, shuffled at start. When a player "rolls", a card from 
 * the deck is picked. A few cards before the deck is empty, the deck is reshuffled again.
 * Each dice number is added to it's weight.   
 * 
 */
public class CardsDeckDice implements Dice
{
    private static final long serialVersionUID = -9052747293803551710L;
    List<Integer> diceRolls = new ArrayList<Integer>();

    @Override
    public void roll(Random random)
    {
    }

    @Override
    public DiceWidget createDiceWidget(DiceWidgetFactory factory)
    {
        return factory.createCardsDeckDiceWidget();
    }

}
