package org.soc.common.views.widgetsInterface.lobby;

import org.soc.common.server.GameDto;

import com.google.gwt.user.client.ui.IsWidget;

public interface NetworkGameDetailsWidget extends IsWidget {
  public void setGame(GameDto gameInfo);
}
