package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.board.ports.PortListChangedEvent;
import soc.common.board.ports.PortListChangedEventHandler;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class TradeBankBitmapWidget extends AbstractActionWidget implements
        ResourcesChangedEventHandler, PortListChangedEventHandler,
        GamePhaseChangedEventHandler, ClickHandler
{
    PushButton btnTradeBank;
    boolean isTradeBankShown = false;

    public TradeBankBitmapWidget(IGamePanel gamePanel, GamePlayer player)
    {
        super(gamePanel, player);

        btnTradeBank = new PushButton(new Image(Resources.icons().bankTrade()));
        player.getResources().addResourcesChangedEventHandler(this);
        player.getPorts().addPortListChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);

        btnTradeBank.addClickHandler(this);
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
            if (player.getPorts().amountGold(player.getResources()) == 0)
            {
                setEnabled(true);
                return;
            }
        }

        setEnabled(false);
    }

    @Override
    public void onClick(ClickEvent arg0)
    {
        if (isTradeBankShown)
        {
            gamePanel.hideTradeBankPanel();
        }
        else
        {
            gamePanel.showTradeBankPanel();
        }
        isTradeBankShown = !isTradeBankShown;
    }

}
