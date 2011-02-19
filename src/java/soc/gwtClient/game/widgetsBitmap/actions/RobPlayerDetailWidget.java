package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.gwtClient.game.widgetsAbstract.AbstractPlayerDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class RobPlayerDetailWidget extends AbstractPlayerDetailWidget
{
    private RobPlayer robPlayer;

    public RobPlayerDetailWidget(GameWidget gamePanel, RobPlayer robPlayer)
    {
        super(gamePanel, robPlayer.getPlayer());
        this.robPlayer = robPlayer;

        rootPanel.add(new Image(Resources.icons().robber()));
        rootPanel.add(new Image(Resources.card(robPlayer.getStolenResource())));
    }
}
