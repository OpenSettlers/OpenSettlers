package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.standard.RobPlayer;
import org.soc.common.board.resources.Resource;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class RobPlayerDetailWidget extends AbstractActionDetailWidget
{
    private RobPlayer robPlayer;

    public RobPlayerDetailWidget(GameWidget gameWidget, RobPlayer robPlayer)
    {
        super(gameWidget, robPlayer.getPlayer());
        this.robPlayer = robPlayer;

        rootPanel.add(new Image(Resources.mediumIcon(robPlayer)));
        Resource stolenResource = robPlayer.getStolenResource();
        rootPanel.add(new Image(Resources.mediumIcon(stolenResource)));
    }

    @Override
    public GameAction getGameAction()
    {
        return robPlayer;
    }
}
