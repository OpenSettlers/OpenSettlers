package soc.common.internationalization;


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
}