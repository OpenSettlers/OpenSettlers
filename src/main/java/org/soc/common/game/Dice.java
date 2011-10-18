package org.soc.common.game;

import java.io.*;
import java.util.*;

import org.soc.common.views.widgetsInterface.actions.*;
import org.soc.common.views.widgetsInterface.actions.DiceWidget.DiceWidgetFactory;

public interface Dice extends Serializable {
  public void roll(Random random);
  public DiceWidget createDiceWidget(DiceWidgetFactory factory);

  public class StandardDice implements Dice {
    private int dice1;
    private int dice2;

    public int getDiceTotal() {
      return dice1 + dice2;
    }
    public int getDice1() {
      return dice1;
    }
    public StandardDice setDice1(int dice1) {
      this.dice1 = dice1;
      return this;
    }
    @Override public void roll(Random random) {
      dice1 = random.nextInt(6, true);
      dice2 = random.nextInt(6, true);
    }
    public int getDice2() {
      return dice2;
    }
    public StandardDice setDice2(int dice2) {
      this.dice2 = dice2;
      return this;
    }
    @Override public DiceWidget createDiceWidget(DiceWidgetFactory factory) {
      return factory.createStandardDiceWidget();
    }
  }

  /** Test code or cheat :P */
  public class PredictableDice implements Dice {
    private List<Integer> numbersToRoll;

    @Override public void roll(Random random) {}
    @Override public DiceWidget createDiceWidget(DiceWidgetFactory factory) {
      // TODO Auto-generated method stub
      return null;
    }
  }

  /** Represents a deck of 36 cards, shuffled at start. When a player "rolls", a card from the deck
   * is picked. A few cards before the deck is empty, the deck is reshuffled again. Each dice number
   * is added to it's weight. */
  public class CardsDeckDice implements Dice {
    private List<Integer> diceRolls = new ArrayList<Integer>();
    private int resetAtCardsLeft = 5;

    @Override public void roll(Random random) {
      //Reshuffle if needed and grab card from deck
    }
    @Override public DiceWidget createDiceWidget(DiceWidgetFactory factory) {
      return factory.createCardsDeckDiceWidget();
    }
  }

  // TODO implement
  public class CitiesKnightsDice implements Dice {
    @Override public void roll(Random random) {}
    @Override public DiceWidget createDiceWidget(DiceWidgetFactory factory) {
      return factory.createCitiesKnightDiceWidget();
    }
  }

  /** Represents a single 6-sided dice. Rolled when a volcano produces resources by the player
   * currently on turn. Each HexPoint matching the rolled dice number is hit by the volcano. A
   * volcano has 6 HexPoints, corresponding with the rolld number.
   * 
   * When an HexPoint is hit four things can happen. When no town or city is present, nothing
   * happens (1). When a town resides on the HexPoint, it will be blown up (removed from the board,
   * put back into players' stock) (2). If a city is present, a town is put back when the player has
   * a town in stock (3). If a city is present and the player does not have a town in stock, the
   * city is removed and no town is placed back on the city's HexPoint (4). */
  public class VolcanoDice implements Dice {
    private static final long serialVersionUID = -4243164420585542860L;
    private int dice = 0;

    public int getDice() {
      return dice;
    }
    @Override public void roll(Random random) {
      dice = random.nextInt(6, true);
    }
    public VolcanoDice setDice(int dice) {
      this.dice = dice;
      return this;
    }
    @Override public DiceWidget createDiceWidget(DiceWidgetFactory factory) {
      return factory.createVolcanoDiceWidget();
    }
  }
}
