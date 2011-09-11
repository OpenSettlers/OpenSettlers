package org.soc.common.internationalization;

import com.google.gwt.core.client.GWT;

/*
 * Helper class to reduce lines of code 
 */
public class I
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

  public static final OpenSettlersConstants constants = GWT
          .create(OpenSettlersConstants.class);
  public static OpenSettlersActions actions = GWT.create(OpenSettlersActions.class);
  public static OpenSettlersMisc misc = GWT.create(OpenSettlersMisc.class);
  public static OpenSettlersStatuses statuses = GWT
          .create(OpenSettlersStatuses.class);
  public static OpenSettlersLobby lobby = GWT.create(OpenSettlersLobby.class);
  public static UserInterfaceMessages ui = GWT.create(UserInterfaceMessages.class);
}