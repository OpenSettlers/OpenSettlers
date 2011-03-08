package soc.gwtClient.lobby;

import java.util.List;

import soc.common.lobby.GameInfo;
import soc.common.lobby.GameListChangedEvent;
import soc.common.lobby.GameListChangedEventHandler;
import soc.common.views.widgetsInterface.lobby.GameListWidget;
import soc.common.views.widgetsInterface.lobby.LobbyWidget;
import soc.common.views.widgetsInterface.lobby.NetworkGameDetailsWidget;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;

public class GameListBitmapWidget extends Composite implements GameListWidget,
                ClickHandler, GameListChangedEventHandler
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
        lobbyWidget.getLobby().getGames().addGameListChangedEventHandler(this);
    }

    /** @wbp.parser.constructor */
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
        btnOpenTable.addClickHandler(this);

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

    }

    public void removeGame(GameInfo info)
    {

    }

    @Override
    public void onClick(ClickEvent event)
    {
        lobbyWidget.getNewNetworkgameWidget().show();
    }

    @Override
    public void onGameListChanged(GameListChangedEvent event)
    {
        if (event.getAddedGame() != null)
            gamesList.addItem(event.getAddedGame().getName());

        if (event.getRemovedGame() != null)
        {
            for (int i = 0; i < gamesList.getItemCount(); i++)
                if (gamesList.getItemText(i).equals(
                                event.getRemovedGame().getName()))
                {
                    gamesList.removeItem(i);
                    break;
                }
        }
    }

}
