package soc.gwtClient.lobby;

import soc.common.actions.lobby.LobbyAction;
import soc.common.actions.lobby.game.JoinGame;
import soc.common.lobby.GameInfo;
import soc.common.server.entities.UserListChangedEvent;
import soc.common.server.entities.UserListChangedEventHandler;
import soc.common.views.widgetsInterface.lobby.LobbyWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GameSummaryBitmapWidget extends VerticalPanel implements
                GameSummaryWidget, UserListChangedEventHandler
{
    private GameInfo gameInfo;
    private LobbyWidget lobbyWidget;
    private DockPanel dockPanel;
    private ListBox listboxUsers;
    private DockPanel dockPanel_1;
    private Image image;
    private Button buttonJoinGame;
    private Label labelBoardName;
    private HorizontalPanel horizontalPanel;
    private Label labelGameName;
    private Label labelAmountUsers;

    public GameSummaryBitmapWidget(LobbyWidget lobbyWidget, GameInfo gameInfo)
    {
        this();
        this.lobbyWidget = lobbyWidget;
        this.gameInfo = gameInfo;
        gameInfo.getUsers().addUserListChangedEventHandler(this);

        updateGameName();
        updateBoardName();
        updateAmountPlayers();
    }

    private void updateGameName()
    {
        labelGameName.setText(gameInfo.getName());
    }

    private void updateBoardName()
    {
        labelBoardName.setText(gameInfo.getBoardId());
    }

    private void updateAmountPlayers()
    {
        // Update amount of players label
        labelAmountUsers.setText(Integer.toString(lobbyWidget.getLobby()
                        .getUsers().size()));
    }

    /** @wbp.parser.constructor */
    public GameSummaryBitmapWidget()
    {
        setSize("100%", "100%");

        dockPanel = new DockPanel();
        dockPanel.setSpacing(5);
        dockPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        add(dockPanel);

        listboxUsers = new ListBox();
        listboxUsers.setVisibleItemCount(5);
        dockPanel.add(listboxUsers, DockPanel.EAST);
        listboxUsers.setWidth("10em");

        dockPanel_1 = new DockPanel();
        dockPanel_1.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        dockPanel.add(dockPanel_1, DockPanel.SOUTH);
        dockPanel_1.setSize("100%", "100%");

        image = new Image(
                        "WEB-INF/classes/soc/gwtClient/images/defaultTheme/size32/City.png");
        dockPanel_1.add(image, DockPanel.WEST);
        image.setSize("32", "32");

        buttonJoinGame = new Button("Join");
        buttonJoinGame.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                joinGame();
            }
        });
        dockPanel_1.add(buttonJoinGame, DockPanel.EAST);

        labelBoardName = new Label("BoardName");
        labelBoardName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        dockPanel_1.add(labelBoardName, DockPanel.CENTER);

        horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        dockPanel.add(horizontalPanel, DockPanel.NORTH);
        horizontalPanel.setWidth("100%");

        labelGameName = new Label("New label");
        labelGameName.setStyleName("header-label");
        horizontalPanel.add(labelGameName);
        labelGameName.setWidth("164px");

        labelAmountUsers = new Label("New label");
        labelAmountUsers.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        horizontalPanel.add(labelAmountUsers);
    }
    protected void joinGame()
    {
        LobbyAction joinGame = new JoinGame().setGameId(gameInfo.getID())
                        .setUser(lobbyWidget.getUser());

        lobbyWidget.sendLobbyAction(joinGame);
    }

    @Override
    public GameInfo getGameInfo()
    {
        return gameInfo;
    }

    @Override
    public void onUserListChanged(UserListChangedEvent event)
    {
        if (event.getAddedUser() != null)
            listboxUsers.addItem(event.getAddedUser().getName());

        if (event.getRemovedUser() != null)
            for (int i = 0; i < listboxUsers.getItemCount(); i++)
                if (listboxUsers.getItemText(i) == event.getRemovedUser()
                                .getName())
                    listboxUsers.removeItem(i);

        updateAmountPlayers();
        // gameInfo.getSettings().getBoardSettings().getAmountPlayers()
    }
}
