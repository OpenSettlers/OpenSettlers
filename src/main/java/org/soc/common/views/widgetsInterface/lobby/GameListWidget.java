package org.soc.common.views.widgetsInterface.lobby;

import java.util.List;

import org.soc.common.server.GameDto;

import com.google.gwt.user.client.ui.IsWidget;

public interface GameListWidget extends IsWidget
{
  public void setGames(List<GameDto> games);
}
