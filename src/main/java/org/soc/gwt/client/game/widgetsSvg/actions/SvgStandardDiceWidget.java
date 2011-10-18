package org.soc.gwt.client.game.widgetsSvg.actions;

import org.soc.common.game.Dice;
import org.soc.common.game.Dice.StandardDice;
import org.soc.common.game.DiceChangedEvent;
import org.soc.common.game.GamePhaseChangedEvent;
import org.soc.common.game.GamePhaseChangedEvent.GamePhaseChangedHandler;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.TurnPhaseChangedEvent;
import org.soc.common.game.TurnPhaseChangedEvent.TurnPhaseChangedHandler;
import org.soc.common.game.actions.Action.ActionPresenter;
import org.soc.common.game.actions.RollDice;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actions.AbstractStandardDiceWidget;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class SvgStandardDiceWidget extends AbstractStandardDiceWidget implements
        GamePhaseChangedHandler, TurnPhaseChangedHandler
{
  SvgSingleDiceWidget dice1 = new SvgSingleDiceWidget(this);
  SvgSingleDiceWidget dice2 = new SvgSingleDiceWidget(this);
  AbsolutePanel totalNumber = new AbsolutePanel();
  Label lblTotalNumber = new Label();

  public SvgStandardDiceWidget(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    rootPanel.setSize("8em", "3em");
    totalNumber.setSize("2em", "3em");
    totalNumber.add(lblTotalNumber, 0, 0);
    lblTotalNumber.setStyleName("diceLabel");
    rootPanel.add(totalNumber, 0, 0);
    rootPanel.add(dice1.asWidget(), 32, 0);
    rootPanel.add(dice2.asWidget(), 72, 0);
    gameWidget.game().addDiceChangedHandler(this);
    gameWidget.game().addGamePhaseChangedHandler(this);
    gameWidget.game().addTurnPhaseChangedHandler(this);
  }
  private void updateTotalNumber(int number)
  {
    lblTotalNumber.setText(Integer.toString(number));
  }
  @Override protected ComplexPanel createRootPanel()
  {
    return new HorizontalPanel();
  }
  @Override public void setStandardDice(StandardDice standardDice)
  {}
  @Override public void onDiceChanged(DiceChangedEvent event)
  {
    Dice dice = event.getNewDice();
    if (dice instanceof StandardDice)
    {
      StandardDice standardDice = (StandardDice) dice;
      dice1.setNumber(standardDice.getDice1());
      dice2.setNumber(standardDice.getDice2());
      updateTotalNumber(standardDice.getDiceTotal());
    }
  }
  @Override public GamePlayer getPlayer()
  {
    return player;
  }
  @Override public ActionPresenter setEnabled(boolean enabled)
  {
    return this;
  }
  @Override public void clicked()
  {
    RollDice rollDice = new RollDice();
    rollDice.setPlayer(gameWidget.playingPlayer());
    gameWidget.doAction(rollDice);
  }
  @Override public void onGamePhaseChanged(GamePhaseChangedEvent event)
  {
    if (event.getNewPhase().isPlayTurns())
    {
      setEnabled(true);
    }
  }
  @Override public void onTurnPhaseChanged(TurnPhaseChangedEvent event)
  {
    if (event.getNewPhase() != null)
    {
      if (!(event.getNewPhase().isBeforeDiceRoll())
              && gameWidget.game().turn().player()
                      .equals(gameWidget.playingPlayer()))
      {
        setEnabled(true);
      }
      else
      {
        setEnabled(false);
      }
    }
  }
  @Override public Point2D getPosition()
  {
    Point2D point = new Point2D(lblTotalNumber.getAbsoluteLeft(),
            lblTotalNumber.getAbsoluteTop());
    return point;
  }
}
