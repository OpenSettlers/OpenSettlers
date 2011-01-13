package soc.gwtClient.game.abstractWidgets.factories;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.BankStockPanel;
import soc.gwtClient.game.abstractWidgets.BankTradeUI;
import soc.gwtClient.game.abstractWidgets.GameHistoryWidget;
import soc.gwtClient.game.abstractWidgets.PlayersWidget;
import soc.gwtClient.game.abstractWidgets.StatusPanel;
import soc.gwtClient.game.widgets.abstractWidgets.LooseCardsDialog;
import soc.gwtClient.game.widgets.abstractWidgets.PlayerStuffWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourcesGainedWidget;
import soc.gwtClient.game.widgets.abstractWidgets.StealCardWidget;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;

public interface GameWidgetFactory
{
    public PlayersWidget createPlayersWidget();

    public BankStockPanel createBankStockPanel();

    public GameBoardVisual createGameBoard(int width, int height);

    public StatusPanel createStatusDicePanel();

    public GameHistoryWidget createHistoryWidget();

    public BankTradeUI createBankTradeUI();

    public ResourcesGainedWidget createResourcesGainedWidget();

    public PlayerStuffWidget createPlayerStuffWidget(GamePlayer player);

    public StealCardWidget createStealCardWidget(GamePlayer player);

    public LooseCardsDialog createLooseCardsDialog();
}
