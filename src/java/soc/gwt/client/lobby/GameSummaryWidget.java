package soc.gwt.client.lobby;

import soc.common.lobby.GameInfo;

import com.google.gwt.user.client.ui.IsWidget;

/*
 * Widget showing game information in the list of games currently on the server
 */
public interface GameSummaryWidget extends IsWidget
{
    public GameInfo getGameInfo();
}
