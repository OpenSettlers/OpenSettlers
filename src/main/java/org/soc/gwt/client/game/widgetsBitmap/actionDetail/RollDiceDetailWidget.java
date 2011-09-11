package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.RollDice;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;

import com.google.gwt.user.client.ui.Label;

public class RollDiceDetailWidget extends AbstractActionDetailWidget
{
    private RollDice rollDice;

    public RollDiceDetailWidget(GameWidget gameWidget, RollDice rollDice)
    {
        super(gameWidget, rollDice.player());
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
