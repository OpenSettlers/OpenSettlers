package soc.common.board.boards;

import soc.common.board.Board;
import soc.common.board.BoardSettings;
import soc.common.board.Chit;
import soc.common.board.ChitList;
import soc.common.board.HexGrid;
import soc.common.board.HexLocation;
import soc.common.board.RotationPosition;
import soc.common.board.hexes.ClayHex;
import soc.common.board.hexes.DesertHex;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.NoneHex;
import soc.common.board.hexes.OreHex;
import soc.common.board.hexes.RandomHex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.hexes.SheepHex;
import soc.common.board.hexes.TimberHex;
import soc.common.board.hexes.WheatHex;
import soc.common.board.ports.ClayPort;
import soc.common.board.ports.OrePort;
import soc.common.board.ports.RandomPort;
import soc.common.board.ports.SheepPort;
import soc.common.board.ports.ThreeToOnePort;
import soc.common.board.ports.TimberPort;
import soc.common.board.ports.WheatPort;
import soc.common.board.territories.Territory;
import soc.common.game.variants.Standard;

public class StandardFourPlayer extends Board
{
    private static final long serialVersionUID = 7516208031201813989L;

    public StandardFourPlayer()
    {
        super();

        this.hexes = new HexGrid(7, 7);

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

        Territory territory = territories.get(0);
        for (Hex hex : hexes)
            if (hex instanceof RandomHex)
                hex.setTerritory(territory);

        territory.getHexes().add(new DesertHex());
        territory.getHexes().add(new WheatHex());
        territory.getHexes().add(new WheatHex());
        territory.getHexes().add(new WheatHex());
        territory.getHexes().add(new WheatHex());

        territory.getHexes().add(new TimberHex());
        territory.getHexes().add(new TimberHex());
        territory.getHexes().add(new TimberHex());
        territory.getHexes().add(new TimberHex());

        territory.getHexes().add(new OreHex());
        territory.getHexes().add(new OreHex());
        territory.getHexes().add(new OreHex());

        territory.getHexes().add(new ClayHex());
        territory.getHexes().add(new ClayHex());
        territory.getHexes().add(new ClayHex());

        territory.getHexes().add(new SheepHex());
        territory.getHexes().add(new SheepHex());
        territory.getHexes().add(new SheepHex());
        territory.getHexes().add(new SheepHex());

        for (Hex hex : hexes)
            hex.setTerritory(territory);

        for (Chit chit : ChitList.getStandardList())
            territory.getChits().add(chit);

        Territory mainlaind = territories.get(0);
        mainlaind.getPorts().add(new ThreeToOnePort());
        mainlaind.getPorts().add(new ThreeToOnePort());
        mainlaind.getPorts().add(new ThreeToOnePort());
        mainlaind.getPorts().add(new ThreeToOnePort());
        mainlaind.getPorts().add(new TimberPort());
        mainlaind.getPorts().add(new WheatPort());
        mainlaind.getPorts().add(new OrePort());
        mainlaind.getPorts().add(new ClayPort());
        mainlaind.getPorts().add(new SheepPort());

        ((SeaHex) hexes.get(1, 0)).setPort(new RandomPort(hexes.get(1, 0)
                .getLocation(), RotationPosition.DEG120));
        ((SeaHex) hexes.get(3, 0)).setPort(new RandomPort(hexes.get(3, 0)
                .getLocation(), RotationPosition.DEG120));
        ((SeaHex) hexes.get(0, 2)).setPort(new RandomPort(hexes.get(0, 2)
                .getLocation(), RotationPosition.DEG60));
        ((SeaHex) hexes.get(6, 3)).setPort(new RandomPort(hexes.get(6, 3)
                .getLocation(), RotationPosition.DEG240));
        ((SeaHex) hexes.get(0, 4)).setPort(new RandomPort(hexes.get(0, 4)
                .getLocation(), RotationPosition.DEG60));
        ((SeaHex) hexes.get(5, 5)).setPort(new RandomPort(hexes.get(5, 5)
                .getLocation(), RotationPosition.DEG300));
        ((SeaHex) hexes.get(5, 1)).setPort(new RandomPort(hexes.get(5, 1)
                .getLocation(), RotationPosition.DEG180));
        ((SeaHex) hexes.get(1, 6)).setPort(new RandomPort(hexes.get(1, 6)
                .getLocation(), RotationPosition.DEG0));
        ((SeaHex) hexes.get(3, 6)).setPort(new RandomPort(hexes.get(3, 6)
                .getLocation(), RotationPosition.DEG300));

        setSettings();
    }

    private void setSettings()
    {
        boardSettings = new BoardSettings().setMinPlayers(4).setMaxPlayers(4)
                .setDesigner("Ruud Poutsma").setId(
                        "8232fc96-2adb-4ce6-b721-fcdf8b712dbf").setName(
                        "Standard 4 player").setVpToWin(10);

        boardSettings.getSupportedVariants().add(Standard.class);
    }

}
