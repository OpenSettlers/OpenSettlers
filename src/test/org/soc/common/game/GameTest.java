package org.soc.common.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.soc.common.game.GamePlayer.GamePlayerImpl;
import org.soc.common.game.actions.HostStartsGame;
import org.soc.common.game.board.Board;
import org.soc.common.game.board.StandardFourPlayer;
import org.soc.common.server.entities.User.Player;

public class GameTest {
  @Test public void testGame() {
    Game game = new Game();
    Board board = new StandardFourPlayer();
    game.setBoard(board);
    GamePlayer player0 = new GamePlayerImpl()
            .setUser(new Player()
                    .setId(0)
                    .setName("testPlayer0"));
    GamePlayer player1 = new GamePlayerImpl()
            .setUser(new Player()
                    .setId(1)
                    .setName("testPlayer1"));
    GamePlayer player2 = new GamePlayerImpl()
            .setUser(new Player()
                    .setId(2)
                    .setName("testPlayer2"));
    GamePlayer player3 = new GamePlayerImpl()
            .setUser(new Player()
                    .setId(3)
                    .setName("testPlayer3"));
    assertTrue(player0.isNot(player1));
    assertTrue(player0.isNot(player2));
    assertTrue(player0.isNot(player3));
    assertTrue(player1.isNot(player2));
    assertTrue(player1.isNot(player3));
    assertTrue(player2.isNot(player3));
    game.players().add(player0, player1, player2, player3);
    assertTrue(game.players().size() == 4);
    HostStartsGame startGame = (HostStartsGame) new HostStartsGame()
            .setPlayer(player0);
    game.performAction(startGame);
  }
}
