package org.soc.common.views.widgetsInterface.main;

import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.actions.HotSeatActionsPlayersWidget;
import org.soc.common.views.widgetsInterface.dialogs.BankTradeWidget;
import org.soc.common.views.widgetsInterface.dialogs.GameOverDialog;
import org.soc.common.views.widgetsInterface.dialogs.HotseatLooseCardsDialog;
import org.soc.common.views.widgetsInterface.dialogs.LooseCardsDialog;
import org.soc.common.views.widgetsInterface.dialogs.StealCardWidget;
import org.soc.common.views.widgetsInterface.payerInfo.PlayersInfoWidget;

public interface GameWidgetFactory
{
    public PlayersInfoWidget createPlayersWidget();

    public BankStockWidget createBankStockWidget();

    public BoardVisualWidget createBoardVisualWidget();

    public StatusWidget createStatusDiceWidget();

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

    public GameDetailsWidget createDetailsWidget();

    public HotSeatActionsPlayersWidget createHotSeatActionsPlayersWidget();

    public HotSeatTradePlayersWidget createHotSeatTradePlayersWidget();
}
