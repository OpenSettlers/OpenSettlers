package soc.gwtClient.game.widgets.standard.bitmap.playerDetail.actions;

import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.gwtClient.game.abstractWidgets.AbstractPlayerDetailWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class RobPlayerDetailWidget extends AbstractPlayerDetailWidget
{
    private RobPlayer robPlayer;

    public RobPlayerDetailWidget(GamePanel gamePanel, RobPlayer robPlayer)
    {
        super(gamePanel, robPlayer.getPlayer());
        this.robPlayer = robPlayer;

        rootPanel.add(new Image(Resources.icons().robber()));
        rootPanel.add(new Image(Resources.card(robPlayer.getStolenResource())));
    }
}
