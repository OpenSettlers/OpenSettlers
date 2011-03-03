package soc.common.board.boards;

import soc.common.board.Board;
import soc.common.board.HexLocation;
import soc.common.board.RotationPosition;
import soc.common.board.chits.ChitList;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.HexList;
import soc.common.board.hexes.NoneHex;
import soc.common.board.hexes.RandomHex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.layouts.HexGrid;
import soc.common.board.ports.PortList;
import soc.common.board.ports.RandomPort;
import soc.common.board.settings.BoardSettings;
import soc.common.board.territories.Territory;

public class StandardFourPlayer extends Board
{
    private static final long serialVersionUID = 7516208031201813989L;

    public StandardFourPlayer()
    {
        super();

        this.hexes = new HexGrid(7, 7);

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

        // Make all random hexes at mainland
        Territory mainlaind = territories.get(0);
        for (Hex hex : hexes)
            hex.setTerritory(mainlaind);

        // Add a standard list of hexes for 4 player games
        mainlaind.getHexes().addList(HexList.newMainIsland4p());
        mainlaind.setChits(ChitList.newStandard4p());
        mainlaind.setPorts(PortList.newMain4p());

        hexes.get(1, 0).setPort(
                new RandomPort(hexes.get(1, 0).getLocation(),
                        RotationPosition.DEG120));
        hexes.get(3, 0).setPort(
                new RandomPort(hexes.get(3, 0).getLocation(),
                        RotationPosition.DEG120));
        hexes.get(0, 2).setPort(
                new RandomPort(hexes.get(0, 2).getLocation(),
                        RotationPosition.DEG60));
        hexes.get(6, 3).setPort(
                new RandomPort(hexes.get(6, 3).getLocation(),
                        RotationPosition.DEG240));
        hexes.get(0, 4).setPort(
                new RandomPort(hexes.get(0, 4).getLocation(),
                        RotationPosition.DEG60));
        hexes.get(5, 5).setPort(
                new RandomPort(hexes.get(5, 5).getLocation(),
                        RotationPosition.DEG300));
        hexes.get(5, 1).setPort(
                new RandomPort(hexes.get(5, 1).getLocation(),
                        RotationPosition.DEG180));
        hexes.get(1, 6).setPort(
                new RandomPort(hexes.get(1, 6).getLocation(),
                        RotationPosition.DEG0));
        hexes.get(3, 6).setPort(
                new RandomPort(hexes.get(3, 6).getLocation(),
                        RotationPosition.DEG300));

        setDesigner("Ruud Poutsma");
        setId("8232fc96-2adb-4ce6-b721-fcdf8b712dbf");
        setName("Standard 4 player");
        boardSettings = new BoardSettings();
    }
}
