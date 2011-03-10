package soc.gwtClient.lobby;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soc.common.lobby.GameInfo;
import soc.common.lobby.GameListChangedEvent;
import soc.common.lobby.GameListChangedEventHandler;
import soc.common.views.widgetsInterface.lobby.GameListWidget;
import soc.common.views.widgetsInterface.lobby.LobbyWidget;
import soc.common.views.widgetsInterface.lobby.NetworkGameDetailsWidget;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GameListBitmapWidget extends Composite implements GameListWidget,
                ClickHandler, GameListChangedEventHandler
{
    private LobbyWidget lobbyWidget;
    private ScrollPanel gamesListRoot = new ScrollPanel();
    private VerticalPanel gamesList = new VerticalPanel();
    private NetworkGameDetailsWidget networkGameDetailsWidget;
    private Map<GameInfo, GameSummaryWidget> summaryWidgets = new HashMap<GameInfo, GameSummaryWidget>();
    private HorizontalPanel labelGamesStatus;
    private Label labelAmountGamesWaiting = new Label();
    private SimplePanel panelGameDetails;
    private HandlerRegistration gameUsersRegistration;

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

        DockLayoutPanel dockPanel = new DockLayoutPanel(Unit.EM);
        // dockPanel.set
        initWidget(dockPanel);
        dockPanel.setSize("100%", "100%");

        labelGamesStatus = new HorizontalPanel();
        dockPanel.addNorth(labelGamesStatus, 2);
        labelGamesStatus.setWidth("100%");
        labelGamesStatus.add(labelAmountGamesWaiting);

        HorizontalPanel panelNewTable = new HorizontalPanel();
        dockPanel.addNorth(panelNewTable, 3);
        panelNewTable.setWidth("100%");

        Button btnOpenTable = new Button("Open table");
        btnOpenTable.setStyleName("ok-button");
        panelNewTable.add(btnOpenTable);
        btnOpenTable.addClickHandler(this);

        panelGameDetails = new SimplePanel();
        dockPanel.addSouth(panelGameDetails, 5);

        gamesListRoot.add(gamesList);
        dockPanel.add(gamesListRoot);
        gamesListRoot.setWidth("100%");
        gamesListRoot.setHeight("100%");
        gamesList.setWidth("100%");
    }

    @Override
    public void setGames(List<GameInfo> games)
    {

    }

    public void addGame(GameInfo gameInfo)
    {
        GameSummaryWidget newGame = new GameSummaryBitmapWidget(lobbyWidget,
                        gameInfo);
        gamesList.add(newGame);
        summaryWidgets.put(gameInfo, newGame);

        // Update & show game details widget when this user created the game
        if (gameInfo.getHost().equals(lobbyWidget.getUser()))
        {
            panelGameDetails.setWidget(networkGameDetailsWidget);
            networkGameDetailsWidget.setGame(gameInfo);
        }
    }

    /*
     * Removes given game from this list
     */
    private void removeGame(GameInfo info)
    {
        GameSummaryWidget widget = summaryWidgets.get(info);
        if (widget != null)
            gamesList.remove(widget);
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
            addGame(event.getAddedGame());

        if (event.getRemovedGame() != null)
            removeGame(event.getRemovedGame());

        labelAmountGamesWaiting.setText(Integer.toString(lobbyWidget.getLobby()
                        .getGames().getAmountGamesWaiting())
                        + " games waiting");
    }
}