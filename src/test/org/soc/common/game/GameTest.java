package org.soc.common.game;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.soc.common.game.GamePlayer.GamePlayerImpl;
import org.soc.common.game.actions.HostStartsGame;
import org.soc.common.game.board.Board;
import org.soc.common.game.board.StandardFourPlayer;

public class GameTest {
  @Test public void testGame() {
    Board board = new StandardFourPlayer();
    Game game = new Game()
            .setBoard(board);
    GamePlayer player0 = new GamePlayerImpl(0, "testPlayer0");
    GamePlayer player1 = new GamePlayerImpl(1, "testPlayer1");
    GamePlayer player2 = new GamePlayerImpl(2, "testPlayer2");
    GamePlayer player3 = new GamePlayerImpl(3, "testPlayer3");
    assertThatAreUnique(player0, player1, player2, player3);
    game.players().add(player0, player1, player2, player3);
    assertTrue(game.players().size() == 4);
    HostStartsGame startGame = (HostStartsGame) new HostStartsGame()
            .setPlayer(player0);
    game.performAction(startGame);
  }
  private static void assertThatAreUnique(Object... objects) {
    for (int i = 0; i < objects.length; i++) {
      Object object = objects[i];
      for (int j = 0; j < objects.length; j++) {
        if (j != i) {
          Object other = objects[j];
          if (object.equals(other)) {
            fail();
          }
        }
      }
    }
  }
}
