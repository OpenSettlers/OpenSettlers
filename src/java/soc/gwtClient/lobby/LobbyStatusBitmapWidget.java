package soc.gwtClient.lobby;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class LobbyStatusBitmapWidget extends HorizontalPanel implements
                LobbyStatusWidget
{
    private Label labelHeartBeats = new Label();
    private int heartbeats = 0;

    public LobbyStatusBitmapWidget()
    {
        super();
        this.add(labelHeartBeats);
    }

    @Override
    public void onHeartBeat()
    {
        heartbeats++;
        labelHeartBeats.setText("Beat: " + Integer.toString(heartbeats));
    }

}
