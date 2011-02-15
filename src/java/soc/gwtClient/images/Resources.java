package soc.gwtClient.images;

import soc.common.board.Chit;
import soc.common.board.hexes.DesertHex;
import soc.common.board.hexes.DiscoveryHex;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.NoneHex;
import soc.common.board.hexes.RandomHex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.hexes.VolcanoHex;
import soc.common.board.pieces.Army;
import soc.common.board.pieces.City;
import soc.common.board.pieces.LongestRoad;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Town;
import soc.common.board.pieces.abstractPieces.PlayerPiece;
import soc.common.board.ports.Port;
import soc.common.board.ports.ThreeToOnePort;
import soc.common.board.ports.TwoToOneResourcePort;
import soc.common.board.resources.Clay;
import soc.common.board.resources.Diamond;
import soc.common.board.resources.Gold;
import soc.common.board.resources.Ore;
import soc.common.board.resources.Resource;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
import soc.common.bots.Bot;
import soc.common.game.VictoryPointItem;
import soc.common.game.developmentCards.standard.VictoryPoint;
import soc.common.game.gamePhase.DetermineFirstPlayerGamePhase;
import soc.common.game.gamePhase.EndedGamePhase;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.InitialPlacementGamePhase;
import soc.common.game.gamePhase.LobbyGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.BeforeDiceRollTurnPhase;
import soc.common.game.gamePhase.turnPhase.BuildingTurnPhase;
import soc.common.game.gamePhase.turnPhase.RollDiceTurnPhase;
import soc.common.game.gamePhase.turnPhase.TradingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.server.entities.Player;
import soc.common.server.entities.ServerUser;
import soc.common.server.entities.User;
import soc.gwtClient.images.defaultTheme.Icons;
import soc.gwtClient.images.defaultTheme.Images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;

public class Resources
{
    private static Icons icons = GWT.create(Icons.class);
    private static Images images = GWT.create(Images.class);

    public static Images images()
    {
        return images;
    }

    public static Icons icons()
    {
        return icons;
    }

    public static ImageResource island(int id)
    {
        switch (id)
        {
        case 1:
            return icons.island1();
        case 2:
            return icons.island2();
        case 3:
            return icons.island3();
        case 4:
            return icons.island4();
        case 5:
            return icons.island5();
        case 6:
            return icons.island6();
        }
        return null;
    }

    public static ImageResource chit(Chit chit)
    {
        switch (chit.getNumber())
        {
        case 0:
            return icons.randomChit();
        case 2:
            return icons.chit2();
        case 3:
            return icons.chit3();
        case 4:
            return icons.chit4();
        case 5:
            return icons.chit5();
        case 6:
            return icons.chit6();
        case 8:
            return icons.chit8();
        case 9:
            return icons.chit9();
        case 10:
            return icons.chit10();
        case 11:
            return icons.chit11();
        case 12:
            return icons.chit12();
        }

        return null;
    }

    public static ImageResource card(Resource resource)
    {
        if (resource instanceof Wheat)
        {
            return icons.wheatCard();
        }
        if (resource instanceof Timber)
        {
            return icons.timberCard();
        }
        if (resource instanceof Ore)
        {
            return icons.oreCard();
        }
        if (resource instanceof Sheep)
        {
            return icons.sheepCard();
        }
        if (resource instanceof Clay)
        {
            return icons.clayCard();
        }

        return null;
    }

    public static ImageResource piece(PlayerPiece piece)
    {
        if (piece instanceof City)
            return icons.city();

        if (piece instanceof Town)
            return icons.town();

        if (piece instanceof Road)
            return icons.road();

        return null;
    }

    public static ImageResource user(User user)
    {
        if (user instanceof ServerUser)
            return Resources.icons().refereeMedium();

        if (user instanceof Player)
            return Resources.icons().playerMedium();

        if (user instanceof Bot)
            return Resources.icons().botMedium();

        return null;
    }

    public static ImageResource port(Port port)
    {
        if (port instanceof ThreeToOnePort)
        {
            return Resources.icons().threeToOnePort();
        }
        if (port instanceof TwoToOneResourcePort)
        {
            TwoToOneResourcePort twoToOneResourcePort = (TwoToOneResourcePort) port;
            Resource resource = twoToOneResourcePort.getResource();

            if (resource instanceof Wheat)
            {
                return icons.wheatPort();
            }
            if (resource instanceof Timber)
            {
                return icons.timberPort();
            }
            if (resource instanceof Ore)
            {
                return icons.orePort();
            }
            if (resource instanceof Sheep)
            {
                return icons.sheepPort();
            }
            if (resource instanceof Clay)
            {
                return icons.clayPort();
            }
        }

        return null;
    }

    public static ImageResource victoryPointItem(VictoryPointItem vp)
    {
        if (vp instanceof Town)
        {
            return icons.town();
        }
        if (vp instanceof City)
        {
            return icons.city();
        }
        if (vp instanceof VictoryPoint)
        {
            return icons.victoryPoint();
        }
        if (vp instanceof Army)
        {
            return icons.soldier();
        }
        if (vp instanceof LongestRoad)
        {
            return icons.road();
        }
        return null;
    }

    public static ImageResource gamePhase(GamePhase phase)
    {
        if (phase instanceof InitialPlacementGamePhase)
            return icons.initialPlacementGamePhase();

        if (phase instanceof DetermineFirstPlayerGamePhase)
            return icons.determineFirstPlayerGamePhase();

        if (phase instanceof PlayTurnsGamePhase)
            return icons.playTurnsGamePhase();

        if (phase instanceof EndedGamePhase)
            return icons.endedGamePhase();

        if (phase instanceof LobbyGamePhase)
            return icons.lobbyGamePhase();

        return null;
    }

    public static ImageResource turnPhase(TurnPhase phase)
    {
        if (phase instanceof BeforeDiceRollTurnPhase)
        {
            return icons.beforeRollDiceTurnPhase();
        }
        if (phase instanceof RollDiceTurnPhase)
        {
            return icons.rollDiceTurnPhase();
        }
        if (phase instanceof TradingTurnPhase)
        {
            return icons.tradingTurnPhase();
        }
        if (phase instanceof BuildingTurnPhase)
        {
            return icons.buildingTurnPhase();
        }
        return null;
    }

    public static ImageResource hexImage(Hex hex)
    {
        if (hex instanceof ResourceHex)
        {
            Resource resource = ((ResourceHex) hex).getResource();
            if (resource instanceof Wheat)
            {
                return images.wheatHex();
            }
            if (resource instanceof Timber)
            {
                return images.timberHex();
            }
            if (resource instanceof Ore)
            {
                return images.oreHex();
            }
            if (resource instanceof Clay)
            {
                return images.clayHex();
            }
            if (resource instanceof Sheep)
            {
                return images.sheepHex();
            }
            if (resource instanceof Gold)
            {
                return images.goldHex();
            }
            if (resource instanceof Diamond)
            {
                return images.diamondHex();
            }
        }
        if (hex instanceof DesertHex)
        {
            return images.desertHex();
        }
        if (hex instanceof SeaHex)
        {
            return images.seaHex();
        }
        if (hex instanceof VolcanoHex)
        {
            return images.volcanoHex();
        }
        if (hex instanceof NoneHex)
        {
            return images.noneHex();
        }
        if (hex instanceof DiscoveryHex)
        {
            return images.discoveryHex();
        }
        if (hex instanceof RandomHex)
        {
            return images.randomHex();
        }

        return null;
    }

    public static ImageResource hexIcon(Hex hex)
    {
        if (hex instanceof ResourceHex)
        {
            Resource resource = ((ResourceHex) hex).getResource();
            if (resource instanceof Wheat)
            {
                return icons.wheatHex();
            }
            if (resource instanceof Timber)
            {
                return icons.timberHex();
            }
            if (resource instanceof Ore)
            {
                return icons.oreHex();
            }
            if (resource instanceof Clay)
            {
                return icons.clayHex();
            }
            if (resource instanceof Sheep)
            {
                return icons.sheepHex();
            }
            if (resource instanceof Gold)
            {
                return icons.goldHex();
            }
            if (resource instanceof Diamond)
            {
                return icons.jungleHex();
            }
        }
        if (hex instanceof DesertHex)
        {
            return icons.desertHex();
        }
        if (hex instanceof SeaHex)
        {
            return icons.seaHex();
        }
        if (hex instanceof VolcanoHex)
        {
            return icons.volcanoHex();
        }
        if (hex instanceof NoneHex)
        {
            return icons.noneHex();
        }
        if (hex instanceof DiscoveryHex)
        {
            return icons.discoveryHex();
        }
        if (hex instanceof RandomHex)
        {
            return icons.randomHex();
        }

        return null;
    }
}
