package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.actions.gameAction.turnActions.standard.TradePlayer;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.TurnChangedEvent;
import soc.common.game.TurnChangedEventHandler;
import soc.common.game.gamePhase.turnPhase.TurnPhaseChangedEvent;
import soc.common.game.gamePhase.turnPhase.TurnPhaseChangedHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.images.Resources;

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
            .tradePlayer()));
    TradePlayer tradePlayer = new TradePlayer();

    public TradePlayerBitmapWidget(GamePanel gamePanel, GamePlayer player)
    {
        super(gamePanel, player);

        tradePlayer.setPlayer(player);

        player.getResources().addResourcesChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);
        gamePanel.getGame().addTurnPhaseChangedHandler(this);
        gamePanel.getGame().addTurnChangedEventHandler(this);
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
                && gamePanel.getGame().isAllowed(tradePlayer)
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
        gamePanel.getTradePlayerUI().show();
    }

}
