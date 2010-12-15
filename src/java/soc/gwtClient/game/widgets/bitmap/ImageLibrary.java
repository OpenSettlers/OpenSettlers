package soc.gwtClient.game.widgets.bitmap;

import com.google.gwt.resources.client.ImageResource;

import soc.common.board.pieces.PlayerPiece;
import soc.common.board.resources.Resource;

public class ImageLibrary
{
    private static String iconFolder = "icons/";
    private static String imageFolder = "images/";

    public static String getIcon(PlayerPiece playerPiece, int size)
    {
        return iconFolder + Integer.toString(size) + "/" + playerPiece.getName() + ".png";
    }
    public static String getTradeIcon(int size)
    {
        return iconFolder + Integer.toString(size) + "/" + "Trade.png";
    }
    public static String getIcon(Resource resource, int size)
    {
        //TODO: support other sizes
        return iconFolder + Integer.toString(48) + "/" + resource.getName() + "48.png"; 
    }
    public static String getDisabledTrade(int i)
    {
        return iconFolder + Integer.toString(48) + "/NoTrade.png"; 
    }
}
