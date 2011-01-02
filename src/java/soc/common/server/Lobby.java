package soc.common.server;

import java.util.List;

import soc.common.game.Game;
import soc.common.game.logs.ChatLog;
import soc.common.server.data.Player;

public interface Lobby
{
    /*
     * List of players in the lobby
     */
    public List<Player> getPlayers();

    /*
     * Add a player to the lobby
     */
    public void addPlayer(Player player);

    /*
     * List of games pending/playing/finished
     */
    public List<Game> getGames();

    /*
     * A player creates a game
     */
    public void createGame(Game game);

    /*
     * A list of chat messages
     */
    public ChatLog getChatLog();

    /*
     * Removes this player from the lobby
     */
    public void removePlayer(Player player);

    public void say(Player player, String chatMessage);
}
