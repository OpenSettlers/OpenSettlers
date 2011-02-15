package soc.gwtClient.game.widgets.standard.bitmap.playerDetail.actions;

import soc.common.actions.gameAction.turnActions.EndTurn;
import soc.gwtClient.game.abstractWidgets.AbstractPlayerDetailWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class EndTurnDetailWidget extends AbstractPlayerDetailWidget
{
    private EndTurn endTurn;

    public EndTurnDetailWidget(GamePanel gamePanel, EndTurn endTurn)
    {
        super(gamePanel, endTurn.getPlayer());
        this.endTurn = endTurn;

        rootPanel.add(new Image(Resources.icons().endTurn()));
    }

}
