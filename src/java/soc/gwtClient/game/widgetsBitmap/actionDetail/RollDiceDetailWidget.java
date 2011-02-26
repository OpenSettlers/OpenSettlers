package soc.gwtClient.game.widgetsBitmap.actionDetail;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.RollDice;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;

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
