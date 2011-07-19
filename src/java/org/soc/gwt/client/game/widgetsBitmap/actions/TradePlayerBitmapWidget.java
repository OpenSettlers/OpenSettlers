package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.actions.gameAction.trading.TradePlayer;
import org.soc.common.board.resources.ResourcesChangedEvent;
import org.soc.common.board.resources.ResourcesChangedEventHandler;
import org.soc.common.game.GamePhaseChangedEvent;
import org.soc.common.game.GamePhaseChangedEventHandler;
import org.soc.common.game.TurnChangedEvent;
import org.soc.common.game.TurnChangedEventHandler;
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

public class TradePlayerBitmapWidget extends AbstractActionWidget implements
                GamePhaseChangedEventHandler, ResourcesChangedEventHandler,
                ClickHandler, TurnChangedEventHandler, TurnPhaseChangedHandler
{
    PushButton btnTradePlayer = new PushButton(new Image(Resources.icons()
                    .tradePlayer48()));
    TradePlayer tradePlayer = new TradePlayer();

    public TradePlayerBitmapWidget(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);

        tradePlayer.setPlayer(player);

        player.getResources().addResourcesChangedEventHandler(this);
        gameWidget.getGame().addGamePhaseChangedEventHandler(this);
        gameWidget.getGame().addTurnPhaseChangedHandler(this);
        gameWidget.getGame().addTurnChangedEventHandler(this);
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
        checkEnabled();
    }

    private void enableUI()
    {
        btnTradePlayer.setEnabled(true);
    }

    private void disableUI()
    {
        btnTradePlayer.setEnabled(false);
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

    @Override
    public void onTurnChanged(TurnChangedEvent event)
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
        if (enabled && player.isOnTurn()
                        && gameWidget.getGame().isAllowed(tradePlayer)
                        && player.getResources().size() > 0)
        {
            enableUI();
            return;
        }

        disableUI();
    }

    @Override
    public void onClick(ClickEvent arg0)
    {
        gameWidget.getTradePlayerUI().show();
    }

}
