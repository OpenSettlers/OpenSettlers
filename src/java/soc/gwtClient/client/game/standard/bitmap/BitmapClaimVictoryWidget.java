package soc.gwtClient.client.game.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Player;
import soc.gwtClient.client.game.AbstractActionWidget;
import soc.gwtClient.client.game.IGamePanel;

public class BitmapClaimVictoryWidget extends AbstractActionWidget
{
    PushButton btnClaimVictory = new PushButton(new Image("icons/48/Cup48.png"));
    
    public BitmapClaimVictoryWidget(IGamePanel gamePanel, Player player)
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
        return btnClaimVictory;
    }

    @Override
    public void updateState()
    {
        // TODO Auto-generated method stub

    }

}