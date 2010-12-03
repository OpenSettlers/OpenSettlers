package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

public class BitmapBuildTownWidget extends AbstractActionWidget
{
    PushButton btnBuildTown = new PushButton(new Image("icons/48/Town48.png"));
    
    public BitmapBuildTownWidget(IGamePanel gamePanel, Player player)
    {
        super(gamePanel, player);
    }

    @Override
    public TurnAction getNewAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateState()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public Widget asWidget()
    {
        return btnBuildTown;
    }

}
