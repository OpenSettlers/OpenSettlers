package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.EndTurn;
import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

public class BitmapEndTurnWidget extends AbstractActionWidget
{
    public PushButton btnEndTurn = new PushButton(new Image("icons/32/EndTurn32.png"));

    public BitmapEndTurnWidget(final IGamePanel gamePanel, final Player player)
    {
        super(gamePanel, player);
        
        btnEndTurn.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                gamePanel.performAction
                (
                    (TurnAction)new EndTurn()
                        .setPlayer(player)
                );
            }
        });
    }
    
    @Override
    public TurnAction getNewAction()
    {
        return null;
    }

    @Override
    public Widget asWidget()
    {
        return btnEndTurn;
    }


    @Override
    public void updateState()
    {
        // TODO Auto-generated method stub
        
    }
}
