package soc.gwtClient.images;

import soc.common.board.chits.Chit;
import soc.common.bots.Bot;
import soc.common.game.phases.DetermineFirstPlayerGamePhase;
import soc.common.game.phases.EndedGamePhase;
import soc.common.game.phases.GamePhase;
import soc.common.game.phases.InitialPlacementGamePhase;
import soc.common.game.phases.LobbyGamePhase;
import soc.common.game.phases.PlayTurnsGamePhase;
import soc.common.game.phases.turnPhase.BeforeDiceRollTurnPhase;
import soc.common.game.phases.turnPhase.BuildingTurnPhase;
import soc.common.game.phases.turnPhase.RollDiceTurnPhase;
import soc.common.game.phases.turnPhase.TradingTurnPhase;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.server.entities.Player;
import soc.common.server.entities.ServerUser;
import soc.common.server.entities.User;
import soc.common.views.meta.Meta;
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

    public ImageResource smallIcon(Meta meta)
    {
        return meta.icon().icon16();
    }

    public ImageResource mediumIcon(Meta meta)
    {
        return meta.icon().icon32();
    }

    public ImageResource largeIcon(Meta meta)
    {
        return meta.icon().icon48();
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
}
