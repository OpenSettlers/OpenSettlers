package soc.gwtClient.client.game.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Player;
import soc.gwtClient.client.game.AbstractActionWidget;
import soc.gwtClient.client.game.IGamePanel;

public class BitmapBankTradeWidget extends AbstractActionWidget
{
    PushButton btnBankTrade = new PushButton(new Image("icons/48/Bank48.png"));
    
    public BitmapBankTradeWidget(IGamePanel gamePanel, Player player)
    {
        super(gamePanel, player);
    }

    @Override
    public TurnAction getNewAction()
    {
        return null;
    }

    @Override
    public Widget asWidget()
    {
        return btnBankTrade;
    }

    @Override
    public void updateState()
    {
        // TODO Auto-generated method stub

    }

}
