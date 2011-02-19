package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.turnActions.EndTurn;
import soc.gwtClient.game.widgetsAbstract.AbstractPlayerDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class EndTurnDetailWidget extends AbstractPlayerDetailWidget
{
    private EndTurn endTurn;

    public EndTurnDetailWidget(GameWidget gamePanel, EndTurn endTurn)
    {
        super(gamePanel, endTurn.getPlayer());
        this.endTurn = endTurn;

        rootPanel.add(new Image(Resources.icons().endTurn()));
    }

}
