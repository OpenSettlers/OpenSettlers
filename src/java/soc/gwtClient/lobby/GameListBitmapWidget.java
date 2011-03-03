package soc.gwtClient.lobby;

import java.util.List;

import soc.common.server.GameInfo;
import soc.common.views.widgetsInterface.lobby.GameListWidget;
import soc.common.views.widgetsInterface.lobby.LobbyWidget;
import soc.common.views.widgetsInterface.lobby.NetworkGameDetailsWidget;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;

public class GameListBitmapWidget extends Composite implements GameListWidget
{
    private LobbyWidget lobbyWidget;
    private ListBox gamesList = new ListBox();
    private DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);
    private NetworkGameDetailsWidget networkGameDetailsWidget;

    public GameListBitmapWidget(LobbyWidget lobbyWidget)
    {
        this();
        this.lobbyWidget = lobbyWidget;

        networkGameDetailsWidget = new NetworkGameDetailsBitmapWidget();
    }

    public GameListBitmapWidget()
    {
        super();

        DockPanel dockPanel = new DockPanel();
        dockPanel.setSpacing(5);
        initWidget(dockPanel);
        dockPanel.setSize("100%", "100%");

        HorizontalPanel labelGamesStatus = new HorizontalPanel();
        dockPanel.add(labelGamesStatus, DockPanel.NORTH);
        labelGamesStatus.setSize("100%", "2em");

        HorizontalPanel panelNewTable = new HorizontalPanel();
        dockPanel.add(panelNewTable, DockPanel.NORTH);
        panelNewTable.setSize("100%", "2em");

        Button btnOpenTable = new Button("Open table");
        btnOpenTable.setStyleName("ok-button");
        panelNewTable.add(btnOpenTable);

        SimplePanel panelGameDetails = new SimplePanel();
        dockPanel.add(panelGameDetails, DockPanel.SOUTH);

        gamesList.setVisibleItemCount(40);
    }

    @Override
    public void setGames(List<GameInfo> games)
    {

    }

    public void addGame(GameInfo info)
    {
        lobbyWidget.getNewNetworkgameWidget().show();
    }

    public void removeGame(GameInfo info)
    {

    }

}
