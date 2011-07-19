package soc.gwt.client.game.widgetsBitmap.actionDetail;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turns.EndTurn;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class EndTurnDetailWidget extends AbstractActionDetailWidget
{
    private EndTurn endTurn;

    public EndTurnDetailWidget(GameWidget gameWidget, EndTurn endTurn)
    {
        super(gameWidget, endTurn.getPlayer());
        this.endTurn = endTurn;

        rootPanel.add(new Image(Resources.mediumIcon(endTurn)));
    }

    @Override
    public GameAction getGameAction()
    {
        return endTurn;
    }

}