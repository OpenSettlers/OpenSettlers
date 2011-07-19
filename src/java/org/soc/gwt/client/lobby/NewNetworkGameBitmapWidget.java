package org.soc.gwt.client.lobby;

import org.soc.common.actions.lobby.game.CreateGame;
import org.soc.common.lobby.GameInfo;
import org.soc.common.lobby.GameInfoImpl;
import org.soc.common.views.widgetsInterface.generic.BoardPicker;
import org.soc.common.views.widgetsInterface.lobby.LobbyWidget;
import org.soc.common.views.widgetsInterface.lobby.NewNetworkGameWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.BoardPickerBitmapWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NewNetworkGameBitmapWidget extends PopupPanel implements
                NewNetworkGameWidget
{
    private Button btnOpenTable;
    private Button btnCancel;
    private LobbyWidget lobbyWidget;
    private BoardPicker boardPicker;
    private TextBox textboxName;

    public NewNetworkGameBitmapWidget(LobbyWidget lobbyWidget)
    {
        this();
        this.lobbyWidget = lobbyWidget;
    }

    /** @wbp.parser.constructor */
    public NewNetworkGameBitmapWidget()
    {

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSize("800px", "600px");
        this.add(verticalPanel);

        StackPanel stackPanel = new StackPanel();
        verticalPanel.add(stackPanel);
        stackPanel.setSize("100%", "100%");

        VerticalPanel panelGeneral = new VerticalPanel();
        panelGeneral.setSpacing(10);
        stackPanel.add(panelGeneral, "General", false);
        panelGeneral.setSize("100%", "100%");

        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        horizontalPanel_1
                        .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        panelGeneral.add(horizontalPanel_1);

        Label lblNewLabel = new Label("Name:");
        horizontalPanel_1.add(lblNewLabel);

        textboxName = new TextBox();
        horizontalPanel_1.add(textboxName);

        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        panelGeneral.add(horizontalPanel_2);

        Label lblPlayers = new Label("Players:");
        horizontalPanel_2.add(lblPlayers);

        Label labelAmounPlayers = new Label("New label");
        horizontalPanel_2.add(labelAmounPlayers);

        HorizontalPanel horizontalPanel_3 = new HorizontalPanel();
        panelGeneral.add(horizontalPanel_3);

        Label lblHost = new Label("Host:");
        horizontalPanel_3.add(lblHost);

        Label labelHost = new Label("New label");
        horizontalPanel_3.add(labelHost);

        VerticalPanel panelBoard = new VerticalPanel();
        stackPanel.add(panelBoard, "Board", false);
        panelBoard.setSize("100%", "100%");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
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
        boardPicker = new BoardPickerBitmapWidget();
        panelBoard.add(boardPicker);
        boardPicker.selectFirst();
    }

    private void createNewGame()
    {
        // GameSettings gameSettings = new GameSettings();
        GameInfo gameInfo = new GameInfoImpl(textboxName.getText(),
                        lobbyWidget.getUser(),
                        "8232fc96-2adb-4ce6-b721-fcdf8b712dbf");
        lobbyWidget.sendLobbyAction(new CreateGame().setGameInfo(gameInfo)
                        .setUser(lobbyWidget.getUser()));
    }
}
