package soc.gwtClient.lobby;

import soc.common.lobby.Lobby;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LobbyStatusBitmapWidget extends VerticalPanel implements
                LobbyStatusWidget
{
    private Label labelHeartBeats = new Label();
    private Label labelError = new Label();
    private Label labelConnectionStatus = new Label();
    private int heartbeats = 0;
    private Lobby lobby;

    public LobbyStatusBitmapWidget(Lobby lobby)
    {
        this.lobby = lobby;
        this.add(labelHeartBeats);
        this.add(labelError);
        this.add(labelConnectionStatus);
    }

    @Override
    public void onHeartBeat()
    {
        heartbeats++;
        labelHeartBeats.setText("Beat: " + Integer.toString(heartbeats));
    }

    @Override
    public void setError(String error)
    {
        labelError.setText("Error: " + error);
    }

    @Override
    public void setConnected()
    {
        labelConnectionStatus.setText("Connected");
    }

    @Override
    public void setDisconnected()
    {
        labelConnectionStatus.setText("XX Disconnected XX");
    }
}
