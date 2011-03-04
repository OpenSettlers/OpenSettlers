package soc.common.server;

import soc.common.actions.lobby.CreateGame;
import soc.common.game.settings.GameSettings;
import soc.common.lobby.GameInfo;
import soc.common.lobby.GameInfoImpl;
import soc.common.views.widgetsInterface.lobby.LobbyWidget;
import soc.common.views.widgetsInterface.lobby.NewNetworkGameWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NewNetworkGameBitmapWidget extends PopupPanel implements
        NewNetworkGameWidget
{
    private Button btnOpenTable;
    private Button btnCancel;
    private TextBox textboxGameName;
    private LobbyWidget lobbyWidget;

    public NewNetworkGameBitmapWidget(LobbyWidget lobbyWidget)
    {
        this();
        this.lobbyWidget = lobbyWidget;
    }

    /**
     * @wbp.parser.constructor
     */
    public NewNetworkGameBitmapWidget()
    {

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSize("100%", "100%");
        this.add(verticalPanel);

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel
                .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        verticalPanel.add(horizontalPanel);
        horizontalPanel.setSize("100%", "5em");

        btnCancel = new Button("Cancel");
        btnCancel.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                hide();
            }
        });
        btnCancel.setStyleName("cancel-button");
        horizontalPanel.add(btnCancel);

        btnOpenTable = new Button("Open table");
        btnOpenTable.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                createNewGame();
            }
        });
        btnOpenTable.setStyleName("ok-button");
        horizontalPanel.add(btnOpenTable);

        TabPanel tabPanel = new TabPanel();
        verticalPanel.add(tabPanel);
        tabPanel.setSize("100%", "100%");

        TabPanel tabGeneral = new TabPanel();
        tabPanel.add(tabGeneral, "General", false);
        tabGeneral.setSize("100%", "100%");

        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        horizontalPanel_1
                .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        tabGeneral.add(horizontalPanel_1, "New tab", false);
        horizontalPanel_1.setSize("5cm", "2em");

        Label lblName = new Label("Name:");
        horizontalPanel_1.add(lblName);

        textboxGameName = new TextBox();
        horizontalPanel_1.add(textboxGameName);

        TabPanel tabBoard = new TabPanel();
        tabPanel.add(tabBoard, "Board", false);
        tabBoard.setSize("5cm", "3cm");

        TabPanel tabSettings = new TabPanel();
        tabPanel.add(tabSettings, "Settings", false);
        tabSettings.setSize("5cm", "3cm");

        TabPanel tabVariants = new TabPanel();
        tabPanel.add(tabVariants, "Variants", false);
        tabVariants.setSize("5cm", "3cm");
    }

    private void createNewGame()
    {
        GameSettings gameSettings = new GameSettings();
        GameInfo gameInfo = new GameInfoImpl(textboxGameName.getText(),
                lobbyWidget.getPlayer(),
                "8232fc96-2adb-4ce6-b721-fcdf8b712dbf", gameSettings);
        lobbyWidget.sendLobbyAction(new CreateGame().setGameInfo(gameInfo));
    }
}
