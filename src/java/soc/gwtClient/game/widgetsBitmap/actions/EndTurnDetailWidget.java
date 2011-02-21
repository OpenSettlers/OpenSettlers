package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turns.EndTurn;
import soc.gwtClient.game.widgetsAbstract.AbstractActionDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class EndTurnDetailWidget extends AbstractActionDetailWidget
{
    private EndTurn endTurn;

    public EndTurnDetailWidget(GameWidget gameWidget, EndTurn endTurn)
    {
        super(gameWidget, endTurn.getPlayer());
        this.endTurn = endTurn;

        rootPanel.add(new Image(Resources.icons().endTurn()));
    }

    @Override
    public GameAction getGameAction()
    {
        return endTurn;
    }

}
