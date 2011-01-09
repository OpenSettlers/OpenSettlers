package soc.gwtClient.game.abstractWidgets;

import soc.common.actions.gameAction.GameAction;
import soc.common.board.pieces.PlayerPiece;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.factories.GameWidgetFactory;
import soc.gwtClient.game.behaviour.GameBehaviour;
import soc.gwtClient.game.widgets.abstractWidgets.ResourcesGainedWidget;
import soc.gwtClient.game.widgets.abstractWidgets.StealCardWidget;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;

public interface GamePanel
{
    // The current game
    public Game getGame();

    // Called by ActionWidget to execute a TurnAction
    public void startAction(GameAction chat);

    // Called by ActionWidgets to notify a BankTrade is needed
    public void requestBankTrade(PlayerPiece piece, GamePlayer player);

    public StealCardWidget getStealCardWidget();

    // Returns the player currrently playing.
    // This may change in a hotseat game
    public GamePlayer getPlayingPlayer();

    public void showTradePlayersPanel();

    public PlayersWidget getPlayersWidget();

    public ResourcesGainedWidget getResourcesGainedWidget();

    public void showTradeBankPanel();

    public void hideTradePlayersPanel();

    public void hideTradeBankPanel();

    public GameBoardVisual getGameBoardVisual();

    public void doneBehaviour(GameBehaviour behaviour);

    public GameWidgetFactory createGameWidgetFactory();

    public void sendAction(GameAction gameAction);
}
