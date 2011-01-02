package soc.common.game;

import java.util.List;

import soc.common.board.Board;
import soc.common.board.HexLocation;
import soc.common.board.resources.ResourceList;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.logs.ActionsQueue;
import soc.common.game.logs.GameLog;
import soc.common.server.data.User;

public interface IGame
{
    // List of actions during the game
    public GameLog getGameLog();

    // List of actions expected to be performed
    public ActionsQueue getActionsQueue();

    // List of players in the game
    public List<GamePlayerImpl> getPlayers();

    // The pirate is no more then a location on a hex
    public HexLocation getPirate();

    // Bank, list of available resources
    public ResourceList getBank();

    // List of users watching the game
    public List<User> getSpectators();

    // Current player on turn
    public GamePlayer getPlayerOnTurn();

    // Player which turn it is next turn
    public GamePlayer getNextPlayerOnTurn();

    // Get the player object instance from an id
    public GamePlayer getPlayer(int playerID);

    // Phase where the game is in
    public GamePhase getGamePhase();

    // Board with hextiles on it
    public Board getBoard();
}
