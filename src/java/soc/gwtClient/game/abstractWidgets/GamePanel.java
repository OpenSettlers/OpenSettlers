package soc.gwtClient.game.abstractWidgets;

import soc.common.actions.gameAction.GameAction;
import soc.common.board.pieces.PlayerPiece;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;

public interface GamePanel
{
    // The current game
    public Game getGame();

    // Called by ActionWidget to execute a TurnAction
    public void startAction(GameAction chat);

    // Called by ActionWidgets to notify a BankTrade is needed
    public void requestBankTrade(PlayerPiece piece, GamePlayer player);

    public ActionsWidget createActionsWidget();

    public PlayersWidget createPlayersWidget();

    public BankStockPanel createBankStockPanel();

    public GameBoardVisual createGameBoard(int width, int height, Game game);

    public HandCardsWidget createHandCardsWidget(GamePlayer player);

    public StatusPanel createStatusDicePanel(GamePanel gamePanel);

    public GameHistoryWidget createHistoryWidget(GamePanel gamePanel);

    public BankTradeUI createBankTradeUI(GamePanel gamePanel);

    // Returns the player currrently playing.
    // This may change in a hotseat game
    public GamePlayer getPlayingPlayer();

    public void showTradePlayersPanel();

    public PlayersWidget getPlayersWidget();

    public void showTradeBankPanel();

    public void hideTradePlayersPanel();

    public void hideTradeBankPanel();
}
