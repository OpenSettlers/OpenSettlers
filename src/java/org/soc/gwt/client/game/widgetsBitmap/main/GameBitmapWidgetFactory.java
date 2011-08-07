package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.dialogs.BankTradeWidget;
import org.soc.common.views.widgetsInterface.dialogs.GameOverDialog;
import org.soc.common.views.widgetsInterface.dialogs.HotseatLooseCardsDialog;
import org.soc.common.views.widgetsInterface.dialogs.LooseCardsDialog;
import org.soc.common.views.widgetsInterface.dialogs.StealCardWidget;
import org.soc.common.views.widgetsInterface.main.BankStockWidget;
import org.soc.common.views.widgetsInterface.main.ChatWidget;
import org.soc.common.views.widgetsInterface.main.DebugWidget;
import org.soc.common.views.widgetsInterface.main.GameDetailsWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.GameWidgetFactory;
import org.soc.common.views.widgetsInterface.main.HistoryWidget;
import org.soc.common.views.widgetsInterface.main.PlayerStuffWidget;
import org.soc.common.views.widgetsInterface.main.QueueWidget;
import org.soc.common.views.widgetsInterface.main.ResourcesGainedWidget;
import org.soc.common.views.widgetsInterface.main.StatusWidget;
import org.soc.common.views.widgetsInterface.payerInfo.PlayersInfoWidget;
import org.soc.gwt.client.game.widgetsAbstract.main.AbstractBankStockWidget;
import org.soc.gwt.client.game.widgetsBitmap.actions.HotSeatActionsPlayersWidgetImpl;
import org.soc.gwt.client.game.widgetsBitmap.dialogs.HotSeatGameOverDialog;
import org.soc.gwt.client.game.widgetsBitmap.dialogs.HotseatLooseCards;
import org.soc.gwt.client.game.widgetsBitmap.dialogs.StealCardDialog;
import org.soc.gwt.client.game.widgetsBitmap.dialogs.TradeBankDialog;
import org.soc.gwt.client.game.widgetsBitmap.status.StatusBitmapPanel;
import org.soc.gwt.client.game.widgetsSvg.BoardVisualSvgWidget;

public class GameBitmapWidgetFactory implements GameWidgetFactory
{
    GameWidget gameWidget;
    GameDetailsBitmapWidget gameDetailsWidget;

    public GameBitmapWidgetFactory(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;

    }

    private GameDetailsBitmapWidget getGameDetailsWidget()
    {
        if (gameDetailsWidget == null)
            this.gameDetailsWidget = new GameDetailsBitmapWidget(gameWidget);

        return gameDetailsWidget;
    }

    @Override
    public PlayersInfoWidget createPlayersWidget()
    {
        return new PlayersBitmapWidget(gameWidget);
    }

    @Override
    public BankStockWidget createBankStockWidget()
    {
        return new AbstractBankStockWidget(gameWidget.getGame());
    }

    @Override
    public StatusWidget createStatusDiceWidget()
    {
        return new StatusBitmapPanel(gameWidget);
    }

    @Override
    public BankTradeWidget createBankTradeWidget()
    {
        return new TradeBankDialog(gameWidget);
    }

    @Override
    public ResourcesGainedWidget createResourcesGainedWidget()
    {
        return new ResourcesGainedBitmapWidget(gameWidget);
    }

    @Override
    public PlayerStuffWidget createPlayerStuffWidget(GamePlayer player)
    {
        return new PlayerStuffBitmapWidget(gameWidget, player);
    }

    @Override
    public StealCardWidget createStealCardWidget(GamePlayer player)
    {
        return new StealCardDialog(gameWidget, player);
    }

    @Override
    public LooseCardsDialog createLooseCardsDialog()
    {
        return new HotseatLooseCards(gameWidget);
    }

    @Override
    public GameOverDialog createGameOverDialog()
    {
        return new HotSeatGameOverDialog();
    }

    @Override
    public HotseatLooseCardsDialog createHotseatLooseCardsDialog()
    {
        return new HotseatLooseCards(gameWidget);
    }

    @Override
    public BoardVisualSvgWidget createBoardVisualWidget()
    {
        return new BoardVisualSvgWidget(gameWidget);
    }

    @Override
    public HistoryWidget createHistoryWidget()
    {
        return getGameDetailsWidget();
    }

    @Override
    public ChatWidget createChatWidget()
    {
        return getGameDetailsWidget();
    }

    @Override
    public DebugWidget createDebugWidget()
    {
        return getGameDetailsWidget();
    }

    @Override
    public QueueWidget createQueueWidget()
    {
        return getGameDetailsWidget();
    }

    @Override
    public GameDetailsWidget createDetailsWidget()
    {
        return getGameDetailsWidget();
    }

    @Override
    public HotSeatActionsPlayersWidgetImpl createHotSeatActionsPlayersWidget()
    {
        return new HotSeatActionsPlayersWidgetImpl(gameWidget, this);
    }

    @Override
    public HotSeatTradePlayersWidgetImpl createHotSeatTradePlayersWidget()
    {
        return new HotSeatTradePlayersWidgetImpl(gameWidget);
    }
}
