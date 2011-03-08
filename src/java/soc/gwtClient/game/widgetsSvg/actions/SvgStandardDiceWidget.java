package soc.gwtClient.game.widgetsSvg.actions;

import soc.common.actions.gameAction.standard.RollDice;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.dices.Dice;
import soc.common.game.dices.DiceChangedEvent;
import soc.common.game.dices.StandardDice;
import soc.common.game.phases.turnPhase.TurnPhaseChangedEvent;
import soc.common.game.phases.turnPhase.TurnPhaseChangedHandler;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.actions.ActionWidget;
import soc.common.views.widgetsInterface.generic.Point2D;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsAbstract.actions.AbstractStandardDiceWidget;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class SvgStandardDiceWidget extends AbstractStandardDiceWidget implements
        GamePhaseChangedEventHandler, TurnPhaseChangedHandler
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

        gameWidget.getGame().addDiceChangedEventHandler(this);
        gameWidget.getGame().addGamePhaseChangedEventHandler(this);
        gameWidget.getGame().addTurnPhaseChangedHandler(this);
    }

    private void updateTotalNumber(int number)
    {
        lblTotalNumber.setText(Integer.toString(number));
    }

    @Override
    protected ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }

    @Override
    public void setStandardDice(StandardDice standardDice)
    {

    }

    @Override
    public void onDiceChanged(DiceChangedEvent event)
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

    @Override
    public GamePlayer getPlayer()
    {
        return player;
    }

    @Override
    public ActionWidget setEnabled(boolean enabled)
    {
        return this;
    }

    @Override
    public void clicked()
    {
        RollDice rollDice = new RollDice();
        rollDice.setPlayer(gameWidget.getPlayingPlayer());
        gameWidget.sendAction(rollDice);
    }

    @Override
    public void onGamePhaseChanged(GamePhaseChangedEvent event)
    {
        if (event.getNewPhase().isPlayTurns())
        {
            setEnabled(true);
        }
    }

    @Override
    public void onTurnPhaseChanged(TurnPhaseChangedEvent event)
    {
        if (event.getNewPhase() != null)
        {
            if (!(event.getNewPhase().isBeforeDiceRoll())
                    && gameWidget.getGame().getCurrentTurn().getPlayer()
                            .equals(gameWidget.getPlayingPlayer()))
            {
                setEnabled(true);
            }
            else
            {
                setEnabled(false);
            }
        }
    }

    @Override
    public Point2D getPosition()
    {
        Point2D point = new Point2D(lblTotalNumber.getAbsoluteLeft(),
                lblTotalNumber.getAbsoluteTop());
        return point;
    }
}
