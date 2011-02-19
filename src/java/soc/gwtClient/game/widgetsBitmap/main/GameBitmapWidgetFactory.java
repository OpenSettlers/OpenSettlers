package soc.gwtClient.game.widgetsBitmap.main;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.AbstractBankStockWidget;
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
import soc.gwtClient.game.widgetsInterface.playerDetail.PlayersInfoWidget;
import soc.gwtClient.game.widgetsSvg.BoardVisualSvgWidget;

public class GameBitmapWidgetFactory implements GameWidgetFactory
{
    GameWidget gamePanel;
    GameDetailsWidget gameDetailsWidget;

    public GameBitmapWidgetFactory(GameWidget gamePanel)
    {
        super();
        this.gamePanel = gamePanel;

        this.gameDetailsWidget = new GameDetailsWidget(gamePanel);
    }

    @Override
    public PlayersInfoWidget createPlayersWidget()
    {
        return new PlayersBitmapWidget(gamePanel);
    }

    @Override
    public BankStockWidget createBankStockWidget()
    {
        return new AbstractBankStockWidget(gamePanel.getGame());
    }

    @Override
    public StatusWidget createStatusDiceWidget()
    {
        return new StatusBitmapPanel(gamePanel);
    }

    @Override
    public BankTradeWidget createBankTradeWidget()
    {
        return new TradeBankDialog(gamePanel);
    }

    @Override
    public ResourcesGainedWidget createResourcesGainedWidget()
    {
        return new ResourcesGainedBitmapWidget(gamePanel);
    }

    @Override
    public PlayerStuffWidget createPlayerStuffWidget(GamePlayer player)
    {
        return new PlayerStuffBitmapWidget(gamePanel, player);
    }

    @Override
    public StealCardWidget createStealCardWidget(GamePlayer player)
    {
        return new StealCardDialog(gamePanel, player);
    }

    @Override
    public LooseCardsDialog createLooseCardsDialog()
    {
        return new HotseatLooseCards(gamePanel);
    }

    @Override
    public GameOverDialog createGameOverDialog()
    {
        return new HotSeatGameOverDialog();
    }

    @Override
    public HotseatLooseCardsDialog createHotseatLooseCardsDialog()
    {
        return new HotseatLooseCards(gamePanel);
    }

    @Override
    public BoardVisualSvgWidget createBoardVisualWidget()
    {
        return new BoardVisualSvgWidget(gamePanel);
    }

    @Override
    public HistoryWidget createHistoryWidget()
    {
        return gameDetailsWidget;
    }

    @Override
    public ChatWidget createChatWidget()
    {
        return gameDetailsWidget;
    }

    @Override
    public DebugWidget createDebugWidget()
    {
        return gameDetailsWidget;
    }

    @Override
    public QueueWidget createQueueWidget()
    {
        return gameDetailsWidget;
    }

    @Override
    public GameDetailsWidget createDetailsWidget()
    {
        return gameDetailsWidget;
    }

    @Override
    public HotSeatActionsPlayersWidget createHotSeatActionsPlayersWidget()
    {
        return new HotSeatActionsPlayersWidget(gamePanel, this);
    }

    @Override
    public HotSeatTradePlayersWidget createHotSeatTradePlayersWidget()
    {
        return new HotSeatTradePlayersWidget(gamePanel);
    }
}
