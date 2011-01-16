package soc.common.internationalization;

import soc.common.board.pieces.City;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Town;

/*
 * Helper class to reduce lines of code 
 */
public class I18n
{
    private static OpenSettlersInternationalization i18n = new ClientInternationalization();

    public static OpenSettlersInternationalization get()
    {
        return i18n;
    }

    // Call initialize before any other call to get()
    public static void initialize(OpenSettlersInternationalization i18n)
    {
        // I18n.i18n = i18n;
    }

    public static String piece(PlayerPiece piece)
    {
        if (piece instanceof City)
        {
            return get().constants().city();
        }
        if (piece instanceof Town)
        {
            return get().constants().town();
        }
        if (piece instanceof Road)
        {
            return get().constants().road();
        }
        return "";
    }
}