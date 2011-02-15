package soc.gwtClient.game.widgets.standard.bitmap.playerDetail.actions;

import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.gwtClient.game.abstractWidgets.AbstractPlayerDetailWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;

import com.google.gwt.user.client.ui.Label;

public class RollDiceDetailWidget extends AbstractPlayerDetailWidget
{
    private RollDice rollDice;

    public RollDiceDetailWidget(GamePanel gamePanel, RollDice rollDice)
    {
        super(gamePanel, rollDice.getPlayer());
        this.rollDice = rollDice;

        Label diceTotal = new Label(Integer.toString(rollDice.getDice()
                .getDiceTotal()));
        diceTotal.setStyleName("label-title");
        rootPanel.add(diceTotal);
    }

}
