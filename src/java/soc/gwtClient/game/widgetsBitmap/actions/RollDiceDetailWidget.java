package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.standard.RollDice;
import soc.gwtClient.game.widgetsAbstract.AbstractPlayerDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

import com.google.gwt.user.client.ui.Label;

public class RollDiceDetailWidget extends AbstractPlayerDetailWidget
{
    private RollDice rollDice;

    public RollDiceDetailWidget(GameWidget gamePanel, RollDice rollDice)
    {
        super(gamePanel, rollDice.getPlayer());
        this.rollDice = rollDice;

        Label diceTotal = new Label(Integer.toString(rollDice.getDice()
                .getDiceTotal()));
        diceTotal.setStyleName("label-title");
        rootPanel.add(diceTotal);
    }

}
