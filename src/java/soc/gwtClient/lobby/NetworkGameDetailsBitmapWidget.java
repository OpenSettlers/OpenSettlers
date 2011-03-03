package soc.gwtClient.lobby;

import soc.common.views.widgetsInterface.lobby.NetworkGameDetailsWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NetworkGameDetailsBitmapWidget extends VerticalPanel implements
        NetworkGameDetailsWidget
{
    private HorizontalPanel panelName = new HorizontalPanel();
    private Label labelName = new Label("Name");
    private Label labelGameName = new Label();
    private HorizontalPanel panelBoard = new HorizontalPanel();
    private Label labelBoard = new Label("Board");
    private Label labelBoardName = new Label();

    public NetworkGameDetailsBitmapWidget()
    {
        panelName.add(labelName);
        panelName.add(labelGameName);
        add(panelName);

        panelBoard.add(labelBoard);
        panelBoard.add(labelBoardName);
        add(panelBoard);
    }

}
