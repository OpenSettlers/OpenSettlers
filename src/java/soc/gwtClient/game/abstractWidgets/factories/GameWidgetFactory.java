package soc.gwtClient.game.abstractWidgets.factories;

import soc.gwtClient.game.abstractWidgets.ActionsWidget;
import soc.gwtClient.game.abstractWidgets.BankStockPanel;
import soc.gwtClient.game.abstractWidgets.BankTradeUI;
import soc.gwtClient.game.abstractWidgets.GameHistoryWidget;
import soc.gwtClient.game.abstractWidgets.HandCardsWidget;
import soc.gwtClient.game.abstractWidgets.PlayersWidget;
import soc.gwtClient.game.abstractWidgets.StatusPanel;
import soc.gwtClient.game.widgets.abstractWidgets.ResourcesGainedWidget;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;

public interface GameWidgetFactory
{
    public ActionsWidget createActionsWidget();

    public PlayersWidget createPlayersWidget();

    public BankStockPanel createBankStockPanel();

    public GameBoardVisual createGameBoard(int width, int height);

    public HandCardsWidget createHandCardsWidget();

    public StatusPanel createStatusDicePanel();

    public GameHistoryWidget createHistoryWidget();

    public BankTradeUI createBankTradeUI();

    public ResourcesGainedWidget createResourcesGainedWidget();
}
