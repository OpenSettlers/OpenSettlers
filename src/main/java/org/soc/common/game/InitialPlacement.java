package org.soc.common.game;

import java.io.Serializable;

import org.soc.common.game.actions.BuildCity;
import org.soc.common.game.actions.BuildRoad;
import org.soc.common.game.actions.BuildTown;

/** Strategy pattern implementation for a board to perform an initialplacement for towns, cities,
 * roads and ships */
public interface InitialPlacement extends Serializable {
  public void preparePlacement(Game game);

  /** Places a town, road, city, road and a third road in the initial placement phase */
  public class TournamentStart implements InitialPlacement {
    @Override public void preparePlacement(Game game) {
      // Expect each player to place town/road - town/road
      int i = 0;
      boolean back = false;
      // A loop going backward. Each index should be hit twice.
      // Example with 4 players: p1 - p2 - p3 - p4 - p4 - p3 - p2 - p1
      while (i > -1) {
        GamePlayer player = game.players().get(i);
        // Second building should be a city
        if (back)
          // Tournament starting rules, add a city
          game.actionsQueue().enqueue(
                  new BuildCity().setPlayer(player), true);
        else
          // First building to build is a town
          game.actionsQueue().enqueue(
                  new BuildTown().setPlayer(player), true);
        // This action actually might be a BuildShipAction too.
        // TODO: implement this somewhere
        game.actionsQueue().enqueue(new BuildRoad().setPlayer(player), true);
        // if the "back" flag is set, we should decrease the counter
        if (back)
          i--;
        else
          i++;
        // flip the flag when counter reaches maximum value
        // (maximum value equals amount of players)
        if (i == game.players().size()) {
          // next loop is walked with same maximum value
          i--;
          // switch flag
          back = true;
        }
        addThirdRoads(game);
      }
    }
    /* Adds a third road to place for every player, starting with the game starter */
    private void addThirdRoads(Game game) {
      // Loop players by index
      for (GamePlayer player : game.players())
        // Enqueue a BuildRoad action for each player
        game.actionsQueue().enqueue(new BuildRoad().setPlayer(player), true);
    }
  }

  /** Places two towns for a player to begin with */
  public class TwoTowns implements InitialPlacement {
    @Override public void preparePlacement(Game game) {
      // Expect each player to place town/road - town/road
      int i = 0;
      boolean back = false;
      // A loop going backward. Each index should be hit twice.
      // Example with 4 players: p1 - p2 - p3 - p4 - p4 - p3 - p2 - p1
      while (i > -1) {
        GamePlayer player = game.players().get(i);
        // Normal starting rules, add two towns
        game.actionsQueue().enqueue(new BuildTown().setPlayer(player), true);
        // This action actually might be a BuildShipAction too.
        // TODO: implement this somewhere
        game.actionsQueue().enqueue(new BuildRoad().setPlayer(player),
                true);
        // if the "back" flag is set, we should decrease the counter
        if (back)
          i--;
        else
          i++;
        // flip the flag when counter reaches maximum value
        // (maximum value equals amount of players)
        if (i == game.players().size()) {
          // next walk is performed with same maximum value
          i--;
          // switch flag
          back = true;
        }
      }
    }
  }
}
