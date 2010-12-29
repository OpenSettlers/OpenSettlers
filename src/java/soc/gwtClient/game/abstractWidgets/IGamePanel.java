package soc.gwtClient.game.abstractWidgets;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.board.Board;
import soc.common.board.pieces.PlayerPiece;
import soc.common.client.visuals.game.IGameBoardVisual;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.gwtClient.game.Point2D;

public interface IGamePanel
{
    // The current game
    public Game getGame();

    // Called by ActionWidget to execute a TurnAction
    public void startAction(AbstractGameAction chat);

    // Called by ActionWidgets to notify a BankTrade is needed
    public void requestBankTrade(PlayerPiece piece, Player player);

    public IActionsWidget createActionsWidget();

    public IPlayersWidget createPlayersWidget();

    public IBankStockPanel createBankStockPanel();

    public IGameBoardVisual createGameBoard(int width, int height, Board board);

    public IHandCardsWidget createHandCardsWidget(Player player);

    public IStatusPanel createStatusDicePanel(IGamePanel gamePanel);

    public IGameHistoryWidget createHistoryWidget(IGamePanel gamePanel);

    public IBankTradeUI createBankTradeUI(IGamePanel gamePanel);

    // Returns the player currrently playing.
    // This may change in a hotseat game
    public Player getPlayingPlayer();

    public void showTradePlayersPanel();

    public IPlayersWidget getPlayersWidget();

    public void showTradeBankPanel();

    public void hideTradePlayersPanel();

    public void hideTradeBankPanel();
}
