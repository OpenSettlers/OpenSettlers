package soc.gwtClient.images;

import soc.common.board.hexes.DesertHex;
import soc.common.board.hexes.DiscoveryHex;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.NoneHex;
import soc.common.board.hexes.RandomHex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.hexes.VolcanoHex;
import soc.common.board.pieces.City;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Town;
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
        {
            return icons.city();
        }
        if (piece instanceof Town)
        {
            return icons.town();
        }
        if (piece instanceof Road)
        {
            return icons.road();
        }

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

    public ImageResource hexImage(Hex hex)
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

}
