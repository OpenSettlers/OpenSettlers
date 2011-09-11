package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.game.Resource;
import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.RobPlayer;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;

public class RobPlayerDetailWidget extends AbstractActionDetailWidget
{
    private RobPlayer robPlayer;

    public RobPlayerDetailWidget(GameWidget gameWidget, RobPlayer robPlayer)
    {
        super(gameWidget, robPlayer.player());
        this.robPlayer = robPlayer;

        rootPanel.add(new Image(R.mediumIcon(robPlayer)));
        Resource stolenResource = robPlayer.getStolenResource();
        rootPanel.add(new Image(R.mediumIcon(stolenResource)));
    }

    @Override
    public GameAction getGameAction()
    {
        return robPlayer;
    }
}
