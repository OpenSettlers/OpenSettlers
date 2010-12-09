package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.board.ports.PortListChangedEvent;
import soc.common.board.ports.PortListChangedEventHandler;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

public class BitmapTradeBankWidget extends AbstractActionWidget implements 
        ResourcesChangedEventHandler, PortListChangedEventHandler, GamePhaseChangedEventHandler
{
    PushButton btnTradeBank = new PushButton(new Image("icons/48/BankTrade48.png"));
    
    public BitmapTradeBankWidget(IGamePanel gamePanel, Player player)
    {
        super(gamePanel, player);
        
        player.getResources().addResourcesChangedEventHandler(this);
        player.getPorts().addPortListChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);
    }

    @Override
    public Widget asWidget()
    {
        return btnTradeBank;
    }

    @Override
    protected void updateEnabled()
    {
        btnTradeBank.setEnabled(enabled);
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        checkEnabled();
    }
    
    @Override
    public void onPortsChanged(PortListChangedEvent event)
    {
        checkEnabled();
    }
    
    @Override
    public void onGamePhaseChanged(GamePhaseChangedEvent event)
    {
        checkEnabled();
    }

    private void checkEnabled()
    {
        if (onTurn)
        {
            if (player.getPorts().amountGold(player.getResources())==0)
            {
                setEnabled(true);
                return;
            }
        }
        
        setEnabled(false);
    }

}
