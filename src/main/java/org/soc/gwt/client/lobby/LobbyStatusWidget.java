package org.soc.gwt.client.lobby;

import com.google.gwt.user.client.ui.IsWidget;

public interface LobbyStatusWidget extends IsWidget
{
    public void onHeartBeat();
    public void setError(String error);
    public void setConnected();
    public void setDisconnected();
}
