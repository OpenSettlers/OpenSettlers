package org.soc.common.game;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.soc.common.game.GamePlayer.GamePlayerImpl;
import org.soc.common.game.actions.HostStartsGame;
import org.soc.common.game.board.Board;
import org.soc.common.game.board.StandardFourPlayer;

public class GameTest {
  private GamePlayer player0 = new GamePlayerImpl(0, "testPlayer0");
  private GamePlayer player1 = new GamePlayerImpl(1, "testPlayer1");
  private GamePlayer player2 = new GamePlayerImpl(2, "testPlayer2");
  private GamePlayer player3 = new GamePlayerImpl(3, "testPlayer3");

  @Test public void testPlayers() {
    assertAreUnique(player0, player1, player2, player3);
  }
  @Test public void testGame() {
    Board board = new StandardFourPlayer();
    Game game = new Game()
            .setBoard(board);
    game.players().add(player0, player1, player2, player3);
    assertTrue(game.players().size() == 4);
    HostStartsGame startGame = (HostStartsGame) new HostStartsGame()
            .setPlayer(player0);
    assertTrue(game.phase().isLobby());
    game.performAction(startGame);
  }
  /** Fails when any object equals any other object */
  private static void assertAreUnique(Object... objects) {
    for (int i = 0; i < objects.length; i++)
      for (int j = 0; j < objects.length; j++)
        if (j != i && objects[i].equals(objects[j]))
          fail();
  }

  private class GameTester {
    Game game;
    //public GameTester assertState 
  }
}
