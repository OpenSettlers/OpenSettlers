package org.soc.common.game.board;

import org.soc.common.game.ChitList;
import org.soc.common.game.Port.RandomPort;
import org.soc.common.game.PortList;
import org.soc.common.game.Territory;
import org.soc.common.game.board.HexLayout.HexGrid;
import org.soc.common.game.hexes.Hex;
import org.soc.common.game.hexes.HexList;
import org.soc.common.game.hexes.NoneHex;
import org.soc.common.game.hexes.RandomHex;
import org.soc.common.game.hexes.SeaHex;

/** Standard four player board by code instantiation */
public class StandardFourPlayer extends Board {
  public StandardFourPlayer() {
    super();
    // Standard board is 7x7
    hexes = new HexGrid(7, 7);
    addHexes();
    // Make all random hexes at mainland
    Territory mainlaind = territories.get(0);
    for (Hex hex : hexes)
      hex.setTerritory(mainlaind);
    // Add a standard list of hexes for 4 player games
    mainlaind.hexes().addList(HexList.newMainIsland4p());
    mainlaind.setChits(ChitList.newStandard4p());
    mainlaind.setPorts(PortList.newMain4p());
    // addRandomPortAt(1,0,RotationPosition.DEG0);
    // Put 9 placeholders for a randomly asigned port on the board
    hexes.get(1, 0).setPort(
            new RandomPort(hexes.get(1, 0).location(),
                    RotationPosition.DEG120));
    hexes.get(3, 0).setPort(
            new RandomPort(hexes.get(3, 0).location(),
                    RotationPosition.DEG120));
    hexes.get(0, 2).setPort(
            new RandomPort(hexes.get(0, 2).location(),
                    RotationPosition.DEG60));
    hexes.get(6, 3).setPort(
            new RandomPort(hexes.get(6, 3).location(),
                    RotationPosition.DEG240));
    hexes.get(0, 4).setPort(
            new RandomPort(hexes.get(0, 4).location(),
                    RotationPosition.DEG60));
    hexes.get(5, 5).setPort(
            new RandomPort(hexes.get(5, 5).location(),
                    RotationPosition.DEG300));
    hexes.get(5, 1).setPort(
            new RandomPort(hexes.get(5, 1).location(),
                    RotationPosition.DEG180));
    hexes.get(1, 6).setPort(
            new RandomPort(hexes.get(1, 6).location(),
                    RotationPosition.DEG0));
    hexes.get(3, 6).setPort(
            new RandomPort(hexes.get(3, 6).location(),
                    RotationPosition.DEG300));
    // Me me me!
    setDesigner("Ruud Poutsma");
    // Some random uuid
    setId("8232fc96-2adb-4ce6-b721-fcdf8b712dbf");
    setName("Standard 4 player");
    boardSettings = new BoardSettings();
  }
  private void addHexes() {
    // Layout board manually row by row.
    // Row 1
    hexes.add(new NoneHex().setLocation(new HexLocation(0, 0)));
    hexes.add(new SeaHex().setLocation(new HexLocation(1, 0)));
    hexes.add(new SeaHex().setLocation(new HexLocation(2, 0)));
    hexes.add(new SeaHex().setLocation(new HexLocation(3, 0)));
    hexes.add(new SeaHex().setLocation(new HexLocation(4, 0)));
    hexes.add(new NoneHex().setLocation(new HexLocation(5, 0)));
    hexes.add(new NoneHex().setLocation(new HexLocation(6, 0)));
    // Row 2
    hexes.add(new NoneHex().setLocation(new HexLocation(0, 1)));
    hexes.add(new SeaHex().setLocation(new HexLocation(1, 1)));
    hexes.add(new RandomHex().setLocation(new HexLocation(2, 1)));
    hexes.add(new RandomHex().setLocation(new HexLocation(3, 1)));
    hexes.add(new RandomHex().setLocation(new HexLocation(4, 1)));
    hexes.add(new SeaHex().setLocation(new HexLocation(5, 1)));
    hexes.add(new NoneHex().setLocation(new HexLocation(6, 1)));
    // Row 3
    hexes.add(new SeaHex().setLocation(new HexLocation(0, 2)));
    hexes.add(new RandomHex().setLocation(new HexLocation(1, 2)));
    hexes.add(new RandomHex().setLocation(new HexLocation(2, 2)));
    hexes.add(new RandomHex().setLocation(new HexLocation(3, 2)));
    hexes.add(new RandomHex().setLocation(new HexLocation(4, 2)));
    hexes.add(new SeaHex().setLocation(new HexLocation(5, 2)));
    hexes.add(new NoneHex().setLocation(new HexLocation(6, 2)));
    // Row 4
    hexes.add(new SeaHex().setLocation(new HexLocation(0, 3)));
    hexes.add(new RandomHex().setLocation(new HexLocation(1, 3)));
    hexes.add(new RandomHex().setLocation(new HexLocation(2, 3)));
    hexes.add(new RandomHex().setLocation(new HexLocation(3, 3)));
    hexes.add(new RandomHex().setLocation(new HexLocation(4, 3)));
    hexes.add(new RandomHex().setLocation(new HexLocation(5, 3)));
    hexes.add(new SeaHex().setLocation(new HexLocation(6, 3)));
    // Row 5
    hexes.add(new SeaHex().setLocation(new HexLocation(0, 4)));
    hexes.add(new RandomHex().setLocation(new HexLocation(1, 4)));
    hexes.add(new RandomHex().setLocation(new HexLocation(2, 4)));
    hexes.add(new RandomHex().setLocation(new HexLocation(3, 4)));
    hexes.add(new RandomHex().setLocation(new HexLocation(4, 4)));
    hexes.add(new SeaHex().setLocation(new HexLocation(5, 4)));
    hexes.add(new NoneHex().setLocation(new HexLocation(6, 4)));
    // Row 6
    hexes.add(new NoneHex().setLocation(new HexLocation(0, 5)));
    hexes.add(new SeaHex().setLocation(new HexLocation(1, 5)));
    hexes.add(new RandomHex().setLocation(new HexLocation(2, 5)));
    hexes.add(new RandomHex().setLocation(new HexLocation(3, 5)));
    hexes.add(new RandomHex().setLocation(new HexLocation(4, 5)));
    hexes.add(new SeaHex().setLocation(new HexLocation(5, 5)));
    hexes.add(new NoneHex().setLocation(new HexLocation(6, 5)));
    // Row 7
    hexes.add(new NoneHex().setLocation(new HexLocation(0, 6)));
    hexes.add(new SeaHex().setLocation(new HexLocation(1, 6)));
    hexes.add(new SeaHex().setLocation(new HexLocation(2, 6)));
    hexes.add(new SeaHex().setLocation(new HexLocation(3, 6)));
    hexes.add(new SeaHex().setLocation(new HexLocation(4, 6)));
    hexes.add(new NoneHex().setLocation(new HexLocation(5, 6)));
    hexes.add(new NoneHex().setLocation(new HexLocation(6, 6)));
  }
}
