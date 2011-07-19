package soc.gwt.client.game.widgetsBitmap.main;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.dialogs.BankTradeWidget;
import soc.common.views.widgetsInterface.dialogs.GameOverDialog;
import soc.common.views.widgetsInterface.dialogs.HotseatLooseCardsDialog;
import soc.common.views.widgetsInterface.dialogs.LooseCardsDialog;
import soc.common.views.widgetsInterface.dialogs.StealCardWidget;
import soc.common.views.widgetsInterface.main.BankStockWidget;
import soc.common.views.widgetsInterface.main.ChatWidget;
import soc.common.views.widgetsInterface.main.DebugWidget;
import soc.common.views.widgetsInterface.main.GameDetailsWidget;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.main.GameWidgetFactory;
import soc.common.views.widgetsInterface.main.HistoryWidget;
import soc.common.views.widgetsInterface.main.PlayerStuffWidget;
import soc.common.views.widgetsInterface.main.QueueWidget;
import soc.common.views.widgetsInterface.main.ResourcesGainedWidget;
import soc.common.views.widgetsInterface.main.StatusWidget;
import soc.common.views.widgetsInterface.payerInfo.PlayersInfoWidget;
import soc.gwt.client.game.widgetsAbstract.main.AbstractBankStockWidget;
import soc.gwt.client.game.widgetsBitmap.actions.HotSeatActionsPlayersWidgetImpl;
import soc.gwt.client.game.widgetsBitmap.dialogs.HotSeatGameOverDialog;
import soc.gwt.client.game.widgetsBitmap.dialogs.HotseatLooseCards;
import soc.gwt.client.game.widgetsBitmap.dialogs.StealCardDialog;
import soc.gwt.client.game.widgetsBitmap.dialogs.TradeBankDialog;
import soc.gwt.client.game.widgetsBitmap.status.StatusBitmapPanel;
import soc.gwt.client.game.widgetsSvg.BoardVisualSvgWidget;

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
