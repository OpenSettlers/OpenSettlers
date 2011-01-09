package soc.gwtClient.game.widgets;

import soc.gwtClient.game.abstractWidgets.AbstractBankStockWidget;
import soc.gwtClient.game.abstractWidgets.ActionsWidget;
import soc.gwtClient.game.abstractWidgets.BankStockPanel;
import soc.gwtClient.game.abstractWidgets.BankTradeUI;
import soc.gwtClient.game.abstractWidgets.GameHistoryWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.HandCardsWidget;
import soc.gwtClient.game.abstractWidgets.PlayersWidget;
import soc.gwtClient.game.abstractWidgets.StatusPanel;
import soc.gwtClient.game.abstractWidgets.factories.GameWidgetFactory;
import soc.gwtClient.game.dialogs.TradeBankDialog;
import soc.gwtClient.game.widgets.abstractWidgets.ResourcesGainedWidget;
import soc.gwtClient.game.widgets.bitmap.HistoryBitmapWidget;
import soc.gwtClient.game.widgets.bitmap.ResourcesGainedBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.HandCardsBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.PlayersBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.StatusBitmapPanel;
import soc.gwtClient.game.widgets.standard.bitmap.actions.ActionsBitmapWidget;
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
    public ActionsWidget createActionsWidget()
    {
        return new ActionsBitmapWidget(gamePanel, gamePanel.getGame()
                .getPlayers().get(0));
    }

    @Override
    public PlayersWidget createPlayersWidget()
    {
        return new PlayersBitmapWidget(gamePanel.getGame());
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
    public HandCardsWidget createHandCardsWidget()
    {
        return new HandCardsBitmapWidget(gamePanel.getPlayingPlayer());
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
}
