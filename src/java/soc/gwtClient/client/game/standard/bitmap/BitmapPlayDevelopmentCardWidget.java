package soc.gwtClient.client.game.standard.bitmap;

import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Player;
import soc.gwtClient.client.game.AbstractActionWidget;
import soc.gwtClient.client.game.IGamePanel;

public class BitmapPlayDevelopmentCardWidget extends AbstractActionWidget
{
    MenuBar menu = new MenuBar(true);
    
    public BitmapPlayDevelopmentCardWidget(IGamePanel gamePanel, Player player)
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
    public Widget asWidget()
    {
        return menu;
    }

    @Override
    public void updateState()
    {
        // TODO Auto-generated method stub
        
    }

}
