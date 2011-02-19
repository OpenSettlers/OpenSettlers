package soc.gwtClient.game.widgetsInterface.main;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsInterface.dialogs.GameOverDialog;
import soc.gwtClient.game.widgetsInterface.dialogs.HotseatLooseCardsDialog;
import soc.gwtClient.game.widgetsInterface.dialogs.LooseCardsDialog;
import soc.gwtClient.game.widgetsInterface.dialogs.StealCardWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.PlayersInfoWidget;

public interface GameWidgetFactory
{
    public PlayersInfoWidget createPlayersWidget();

    public BankStockWidget createBankStockPanel();

    public BoardVisualWidget createBoardVisualWidget();

    public StatusWidget createStatusDicePanel();

    public HistoryWidget createHistoryWidget();

    public ChatWidget createChatWidget();

    public DebugWidget createDebugWidget();

    public QueueWidget createQueueWidget();

    public BankTradeWidget createBankTradeWidget();

    public ResourcesGainedWidget createResourcesGainedWidget();

    public PlayerStuffWidget createPlayerStuffWidget(GamePlayer player);

    public StealCardWidget createStealCardWidget(GamePlayer player);

    public LooseCardsDialog createLooseCardsDialog();

    public HotseatLooseCardsDialog createHotseatLooseCardsDialog();

    public GameOverDialog createGameOverDialog();
}
