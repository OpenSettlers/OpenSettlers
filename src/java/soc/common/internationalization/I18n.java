package soc.common.internationalization;

import java.io.IOException;

import org.scb.gwt.web.server.i18n.GWTI18N;

/*
 * Helper class to reduce lines of code 
 */
public class I18n
{
    public static OpenSettlersConstants constants;
    public static OpenSettlersActions actions;
    public static OpenSettlersMisc misc;
    public static OpenSettlersStatuses statuses;

    static
    {
        try
        {
            constants = GWTI18N.create(OpenSettlersConstants.class);
            actions = GWTI18N.create(OpenSettlersActions.class);
            statuses = GWTI18N.create(OpenSettlersStatuses.class);
            misc = GWTI18N.create(OpenSettlersMisc.class);
        }
        catch (IOException io)
        {
        }
        finally
        {
        }
    }
}
