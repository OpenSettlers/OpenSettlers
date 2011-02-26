package soc.gwtClient.game.widgetsBitmap.actionDetail;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.RobPlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class RobPlayerDetailWidget extends AbstractActionDetailWidget
{
    private RobPlayer robPlayer;

    public RobPlayerDetailWidget(GameWidget gameWidget, RobPlayer robPlayer)
    {
        super(gameWidget, robPlayer.getPlayer());
        this.robPlayer = robPlayer;

        rootPanel.add(new Image(Resources.icons().robber()));
        rootPanel.add(new Image(robPlayer.getStolenResource().getMeta().icon()
                .iconDefault()));
    }

    @Override
    public GameAction getGameAction()
    {
        return robPlayer;
    }
}
