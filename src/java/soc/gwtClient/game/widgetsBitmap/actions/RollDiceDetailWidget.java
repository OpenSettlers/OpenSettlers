package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.RollDice;
import soc.gwtClient.game.widgetsAbstract.AbstractActionDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

import com.google.gwt.user.client.ui.Label;

public class RollDiceDetailWidget extends AbstractActionDetailWidget
{
    private RollDice rollDice;

    public RollDiceDetailWidget(GameWidget gameWidget, RollDice rollDice)
    {
        super(gameWidget, rollDice.getPlayer());
        this.rollDice = rollDice;

        Label diceTotal = new Label(Integer.toString(rollDice.getDice()
                .getDiceTotal()));
        diceTotal.setStyleName("label-title");
        rootPanel.add(diceTotal);
    }

    @Override
    public GameAction getGameAction()
    {
        return rollDice;
    }

}
