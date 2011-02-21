package soc.gwtClient.game.widgetsBitmap.main;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.main.AbstractBankStockWidget;
import soc.gwtClient.game.widgetsBitmap.actions.HotSeatActionsPlayersWidget;
import soc.gwtClient.game.widgetsBitmap.dialogs.HotSeatGameOverDialog;
import soc.gwtClient.game.widgetsBitmap.dialogs.HotseatLooseCards;
import soc.gwtClient.game.widgetsBitmap.dialogs.StealCardDialog;
import soc.gwtClient.game.widgetsBitmap.dialogs.TradeBankDialog;
import soc.gwtClient.game.widgetsBitmap.status.StatusBitmapPanel;
import soc.gwtClient.game.widgetsInterface.dialogs.GameOverDialog;
import soc.gwtClient.game.widgetsInterface.dialogs.HotseatLooseCardsDialog;
import soc.gwtClient.game.widgetsInterface.dialogs.LooseCardsDialog;
import soc.gwtClient.game.widgetsInterface.dialogs.StealCardWidget;
import soc.gwtClient.game.widgetsInterface.main.BankStockWidget;
import soc.gwtClient.game.widgetsInterface.main.BankTradeWidget;
import soc.gwtClient.game.widgetsInterface.main.ChatWidget;
import soc.gwtClient.game.widgetsInterface.main.DebugWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.HistoryWidget;
import soc.gwtClient.game.widgetsInterface.main.PlayerStuffWidget;
import soc.gwtClient.game.widgetsInterface.main.QueueWidget;
import soc.gwtClient.game.widgetsInterface.main.ResourcesGainedWidget;
import soc.gwtClient.game.widgetsInterface.main.StatusWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.PlayersInfoWidget;
import soc.gwtClient.game.widgetsSvg.BoardVisualSvgWidget;

public class GameBitmapWidgetFactory implements GameWidgetFactory
{
    GameWidget gameWidget;
    GameDetailsWidget gameDetailsWidget;

    public GameBitmapWidgetFactory(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;

    }

    private GameDetailsWidget getGameDetailsWidget()
    {
        if (gameDetailsWidget == null)
            this.gameDetailsWidget = new GameDetailsWidget(gameWidget);

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
    public HotSeatActionsPlayersWidget createHotSeatActionsPlayersWidget()
    {
        return new HotSeatActionsPlayersWidget(gameWidget, this);
    }

    @Override
    public HotSeatTradePlayersWidget createHotSeatTradePlayersWidget()
    {
        return new HotSeatTradePlayersWidget(gameWidget);
    }
}
