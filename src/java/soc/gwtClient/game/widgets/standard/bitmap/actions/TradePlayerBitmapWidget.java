package soc.gwtClient.game.widgets.standard.bitmap.actions;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.actions.gameAction.turnActions.standard.TradePlayer;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.dialogs.TradePlayersDialog;

public class TradePlayerBitmapWidget extends AbstractActionWidget implements 
        GamePhaseChangedEventHandler, ResourcesChangedEventHandler, ClickHandler
{
    PushButton btnTradePlayer = new PushButton(new Image("iconz/48/TradePlayer48.png"));
    TradePlayer tradePlayer = new TradePlayer();

    public TradePlayerBitmapWidget(IGamePanel gamePanel, Player player)
    {
        super(gamePanel, player);
        
        tradePlayer.setPlayer(player);
        
        player.getResources().addResourcesChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);
        btnTradePlayer.addClickHandler(this);
    }

    @Override
    public Widget asWidget()
    {
        return btnTradePlayer;
    }

    @Override
    protected void updateEnabled()
    {
        btnTradePlayer.setEnabled(enabled);
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
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
        if (gamePanel.getGame().getCurrentPhase().isAllowed(tradePlayer) &&
            player.getResources().size() > 0)
        {
            setEnabled(true);
            return;            
        }
        
        setEnabled(false);
    }

    @Override
    public void onClick(ClickEvent arg0)
    {
        gamePanel.showTradePlayersPanel();
    }
}
