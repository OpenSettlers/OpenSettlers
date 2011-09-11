package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.game.actions.EndTurn;
import org.soc.common.game.actions.GameAction;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;

public class EndTurnDetailWidget extends AbstractActionDetailWidget
{
    private EndTurn endTurn;

    public EndTurnDetailWidget(GameWidget gameWidget, EndTurn endTurn)
    {
        super(gameWidget, endTurn.player());
        this.endTurn = endTurn;

        rootPanel.add(new Image(R.mediumIcon(endTurn)));
    }

    @Override
    public GameAction getGameAction()
    {
        return endTurn;
    }

}
