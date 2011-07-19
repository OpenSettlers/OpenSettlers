package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.actions.gameAction.trading.TradeBank;
import org.soc.common.board.ports.PortListChangedEvent;
import org.soc.common.board.ports.PortListChangedEventHandler;
import org.soc.common.board.resources.ResourcesChangedEvent;
import org.soc.common.board.resources.ResourcesChangedEventHandler;
import org.soc.common.game.GamePhaseChangedEvent;
import org.soc.common.game.GamePhaseChangedEventHandler;
import org.soc.common.game.phases.turnPhase.TurnPhaseChangedEvent;
import org.soc.common.game.phases.turnPhase.TurnPhaseChangedHandler;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actions.AbstractActionWidget;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class TradeBankBitmapWidget extends AbstractActionWidget implements
                ResourcesChangedEventHandler, PortListChangedEventHandler,
                GamePhaseChangedEventHandler, ClickHandler,
                TurnPhaseChangedHandler
{
    PushButton btnTradeBank;
    boolean isTradeBankShown = false;
    TradeBank tradeBank = new TradeBank();

    public TradeBankBitmapWidget(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);

        btnTradeBank = new PushButton(
                        new Image(Resources.icons().bankTrade48()));
        player.getResources().addResourcesChangedEventHandler(this);
        player.getPorts().addPortListChangedEventHandler(this);
        gameWidget.getGame().addGamePhaseChangedEventHandler(this);
        gameWidget.getGame().addTurnPhaseChangedHandler(this);

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
        checkEnabled();
    }

    private void enableUI()
    {
        btnTradeBank.setEnabled(true);
    }

    private void disableUI()
    {
        btnTradeBank.setEnabled(false);
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

    @Override
    public void onTurnPhaseChanged(TurnPhaseChangedEvent event)
    {
        checkEnabled();
    }

    private void checkEnabled()
    {
        if (enabled && player.isOnTurn())
        {
            if (gameWidget.getGame().isAllowed(tradeBank)
                            && player.getPorts().amountGold(
                                            player.getResources()) > 0)
            {
                enableUI();
                return;
            }
        }

        disableUI();
    }

    @Override
    public void onClick(ClickEvent arg0)
    {
        gameWidget.getBankTradeDialog().setPieceToTradeFor(null, null);
    }

}
