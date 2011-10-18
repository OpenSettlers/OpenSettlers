package org.soc.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.actions.GameAction;

/*
 * Represent the current status of the game. Possible statuses:
 * -Playing
 * -PlayersDisconnected
 * -PlayersLeft
 * -Ended
 * -Lobby
 * -Setting up
 */
public interface GameStatus extends Serializable {
  /* Whether or not the game can continue while in this state */
  public boolean blocksGame();
  public String getDescription();
  public boolean isWaitingForPlayers();
  public boolean isPlaying();

  /* Status where the game has been ended. */
  public class Ended implements GameStatus
  {
    private static final long serialVersionUID = -6698546004041080852L;

    @Override public boolean blocksGame()
    {
      return false;
    }
    @Override public String getDescription()
    {
      // TODO fix message
      return "This game has ended.";
    }
    @Override public boolean isWaitingForPlayers()
    {
      return false;
    }
    @Override public boolean isPlaying()
    {
      return false;
    }
  }

  public class Playing implements GameStatus {
    private static final long serialVersionUID = -5863963795809197191L;

    @Override public boolean blocksGame() {
      return false;
    }
    @Override public String getDescription() {
      // TODO fix message
      return "Playing the game";
    }
    @Override public boolean isWaitingForPlayers() {
      return false;
    }
    @Override public boolean isPlaying() {
      return true;
    }
  }

  /* When the game does not have enough players to continue with playing */
  public class WaitingForPlayers implements GameStatus
  {
    private static final long serialVersionUID = 6687601384463272579L;

    /* Returns true; when waiting for players, the game can't continue
     * 
     * @see org.soc.common.game.statuses.IGameStatus#isGameBlocking() */
    @Override public boolean blocksGame()
    {
      return true;
    }
    @Override public String getDescription()
    {
      // TODO fix message
      return "Waiting for players";
    }
    @Override public boolean isWaitingForPlayers()
    {
      return true;
    }
    @Override public boolean isPlaying()
    {
      return false;
    }
  }

  /* Status where the game is waiting for a replacement player because a player left the game */
  public class WaitingForReplacementPlayer implements GameStatus
  {
    private static final long serialVersionUID = 574256018106230045L;
    private int amountPlayers = 1;
    private List<GamePlayer> gonePlayers = new ArrayList<GamePlayer>();

    @Override public boolean blocksGame()
    {
      return true;
    }
    public List<GamePlayer> getGonePlayers()
    {
      return gonePlayers;
    }
    @Override public String getDescription()
    {
      // TODO fix message
      return "Waiting for another player to replace ...";
    }
    @Override public boolean isWaitingForPlayers()
    {
      return false;
    }
    @Override public boolean isPlaying()
    {
      return false;
    }
  }

  public class WaitingForTradeResponse implements GameStatus
  {
    private static final long serialVersionUID = -7042958419847540962L;

    @Override public boolean blocksGame()
    {
      return false;
    }
    @Override public String getDescription()
    {
      // TODO fix message
      return "Waiting for players to respond on a trade offer";
    }
    @Override public boolean isWaitingForPlayers()
    {
      return false;
    }
    @Override public boolean isPlaying()
    {
      return false;
    }
  }

  public class WaitingForTurnActions implements GameStatus
  {
    private static final long serialVersionUID = 8620951881762820554L;
    private List<GameAction> blockingActions;

    /* Players may perform turns
     * 
     * @see org.soc.common.game.statuses.IGameStatus#isGameBlocking() */
    @Override public boolean blocksGame()
    {
      return false;
    }
    public List<GameAction> getBlockingActions()
    {
      return blockingActions;
    }
    public WaitingForTurnActions setBlockingActions(
            List<GameAction> blockingActions)
    {
      this.blockingActions = blockingActions;
      return this;
    }
    @Override public String getDescription()
    {
      // TODO fix message
      return "Waiting for a player to finish turn actions";
    }
    @Override public boolean isWaitingForPlayers()
    {
      return false;
    }
    @Override public boolean isPlaying()
    {
      return false;
    }
  }
}