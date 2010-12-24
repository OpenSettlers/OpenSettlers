package soc.common.server;

import java.util.List;

import soc.common.game.Game;
import soc.common.game.Player;
import soc.common.game.logs.IChatLog;

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
    public IChatLog getChatLog();
}
