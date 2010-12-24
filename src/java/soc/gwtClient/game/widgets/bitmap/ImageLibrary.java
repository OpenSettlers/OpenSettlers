package soc.gwtClient.game.widgets.bitmap;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

import soc.common.board.Chit;
import soc.common.board.hexes.AbstractHex;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.ports.Port;
import soc.common.board.ports.RandomPort;
import soc.common.board.ports.ThreeToOnePort;
import soc.common.board.ports.TwoToOneResourcePort;
import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.developmentCards.standard.Monopoly;

public class ImageLibrary
{
    private static String iconFolder = "iconz/";
    private static String imageFolder = "images/";
    public static String getIcon(PlayerPiece piece,int size)
    {
        return iconFolder + Integer.toString(size) + "/" + piece.getName() + ".png"; 
    }
    public static String getIcon(Chit chit2, int size)
    {
        if (chit2.getNumber()==0)
        {
            return iconFolder + Integer.toString(size) + "/chitrandom.png";
        }
        else
        {
            return iconFolder + Integer.toString(size) + "/chit" + chit2.getNumber() + ".png";
        }
    }
    public static String getTradeIcon(int size)
    {
        return iconFolder + Integer.toString(size) + "/" + "Trade.png";
    }
    public static String getIcon(Resource resource, int size)
    {
        //TODO: support other sizes
        return iconFolder + Integer.toString(32) + "/" + resource.getName() + ".png"; 
    }
    public static String getDisabledTrade(int i)
    {
        return iconFolder + Integer.toString(48) + "/NoTrade.png"; 
    }
    public static String getRoadTokenIcon(int size)
    {
        return iconFolder + Integer.toString(size) + "/" + "RoadToken.png";
    }
    public static String getIcon(AbstractHex timberHex, int size)
    {
        return iconFolder + "32/" + timberHex.getName() + ".png"; 
    }
    public static String getIcon(Port threeToOnePort, int size)
    {
        String name = "";
        if (threeToOnePort instanceof TwoToOneResourcePort)
        {
            name = ((TwoToOneResourcePort)threeToOnePort).getResource().getName();
        }
        if (threeToOnePort instanceof ThreeToOnePort)
        {
            name = "31";
        }
        if (threeToOnePort instanceof RandomPort)
        {
            name = "Random";
        }
        // TODO: support more sizes
        return iconFolder + Integer.toString(48) + "/" + name + "Port.png";
    }
    public static String getIcon(DevelopmentCard devCard, int size)
    {
        return iconFolder + Integer.toString(size) + "/" + devCard.getName() + ".png";
    }
}
