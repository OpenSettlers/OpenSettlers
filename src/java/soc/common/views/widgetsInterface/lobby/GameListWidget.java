package soc.common.views.widgetsInterface.lobby;

import java.util.List;

import soc.common.lobby.GameInfo;

import com.google.gwt.user.client.ui.IsWidget;

public interface GameListWidget extends IsWidget
{
    public void setGames(List<GameInfo> games);
}
