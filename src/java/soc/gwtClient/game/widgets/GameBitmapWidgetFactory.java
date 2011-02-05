package soc.gwtClient.game.widgets;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractBankStockWidget;
import soc.gwtClient.game.abstractWidgets.BankStockPanel;
import soc.gwtClient.game.abstractWidgets.BankTradeUI;
import soc.gwtClient.game.abstractWidgets.GameHistoryWidget;
import soc.gwtClient.game.abstractWidgets.GameOverDialog;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.PlayersWidget;
import soc.gwtClient.game.abstractWidgets.StatusPanel;
import soc.gwtClient.game.abstractWidgets.factories.GameWidgetFactory;
import soc.gwtClient.game.dialogs.HotSeatGameOverDialog;
import soc.gwtClient.game.dialogs.HotseatLooseCards;
import soc.gwtClient.game.dialogs.StealCardDialog;
import soc.gwtClient.game.dialogs.TradeBankDialog;
import soc.gwtClient.game.widgets.abstractWidgets.LooseCardsDialog;
import soc.gwtClient.game.widgets.abstractWidgets.PlayerStuffWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourcesGainedWidget;
import soc.gwtClient.game.widgets.abstractWidgets.StealCardWidget;
import soc.gwtClient.game.widgets.bitmap.HistoryBitmapWidget;
import soc.gwtClient.game.widgets.bitmap.ResourcesGainedBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.PlayersBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.status.StatusBitmapPanel;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;
import soc.gwtClient.visuals.svg.GameBoardSvg;

public class GameBitmapWidgetFactory implements GameWidgetFactory
{
    GamePanel gamePanel;

    public GameBitmapWidgetFactory(GamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
    }

    @Override
    public PlayersWidget createPlayersWidget()
    {
        return new PlayersBitmapWidget(gamePanel);
    }

    @Override
    public BankStockPanel createBankStockPanel()
    {
        return new AbstractBankStockWidget(gamePanel.getGame());
    }

    @Override
    public GameBoardVisual createGameBoard(int width, int height)
    {
        return new GameBoardSvg(gamePanel.getGame(), width, height);
    }

    @Override
    public StatusPanel createStatusDicePanel()
    {
        return new StatusBitmapPanel(gamePanel);
    }

    @Override
    public GameHistoryWidget createHistoryWidget()
    {
        return new HistoryBitmapWidget(gamePanel);
    }

    @Override
    public BankTradeUI createBankTradeUI()
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
}
