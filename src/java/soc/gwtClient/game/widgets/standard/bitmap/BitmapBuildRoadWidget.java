package soc.gwtClient.client.game.standard.bitmap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.actions.gameAction.turnActions.standard.*;
import soc.common.board.pieces.Road;
import soc.common.game.Player;
import soc.gwtClient.client.game.AbstractActionWidget;
import soc.gwtClient.client.game.IActionWidget;
import soc.gwtClient.client.game.IGamePanel;


public class BitmapBuildRoadWidget extends AbstractActionWidget
{
    PushButton btnBuildRoad = new PushButton(new Image("icons/48/Road48.png"));
    Road road = new Road();
    
    public BitmapBuildRoadWidget(final IGamePanel gamePanel, final Player player)
    {
        super(gamePanel, player);
        
        btnBuildRoad.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                if (player.getResources().hasAtLeast(road.getCost()))
                {
                    gamePanel.performAction
                    (
                        (TurnAction)new BuildRoad()
                            .setPlayer(player)
                    );
                }
            }
        });
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
        return btnBuildRoad;
    }

    @Override
    public void updateState()
    {
        // TODO Auto-generated method stub
        
    }
}