package org.soc.common.game;

import org.junit.Test;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEvent;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEventHandler;
import org.soc.common.game.pieces.Town;
import org.soc.common.game.pieces.TownList;

public class TownListTest {
  @Test public void test() {
    TownList towns = new TownList();
    towns.addTownsChangedEventHandler(new PlayerPieceListChangedEventHandler<Town>() {
      @Override public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<Town> event) {
        System.out.println();
      }
    });
    towns.add(new Town());
  }
}
