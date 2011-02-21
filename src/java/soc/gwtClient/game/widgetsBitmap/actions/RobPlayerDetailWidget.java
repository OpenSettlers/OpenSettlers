package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.RobPlayer;
import soc.gwtClient.game.widgetsAbstract.AbstractActionDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class RobPlayerDetailWidget extends AbstractActionDetailWidget
{
    private RobPlayer robPlayer;

    public RobPlayerDetailWidget(GameWidget gamePanel, RobPlayer robPlayer)
    {
        super(gamePanel, robPlayer.getPlayer());
        this.robPlayer = robPlayer;

        rootPanel.add(new Image(Resources.icons().robber()));
        rootPanel.add(new Image(Resources.card(robPlayer.getStolenResource())));
    }

    @Override
    public GameAction getGameAction()
    {
        return robPlayer;
    }
}
