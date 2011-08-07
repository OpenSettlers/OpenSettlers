package org.soc.gwt.client.lobby;

import org.soc.common.lobby.GameInfo;
import org.soc.common.views.widgetsInterface.lobby.LobbyWidget;
import org.soc.common.views.widgetsInterface.lobby.NetworkGameDetailsWidget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NetworkGameDetailsBitmapWidget extends Composite implements
                NetworkGameDetailsWidget
{
    private GameInfo gameInfo;
    private final DockPanel dockPanel = new DockPanel();
    private final Label lblBoard = new Label("Board: ");
    private final Label labelBoardName = new Label();
    private final Label labelGameName = new Label();
    private final VerticalPanel verticalPanel = new VerticalPanel();
    private final ListBox listboxUsers = new ListBox();
    private final Label lblNewLabel = new Label("Host player:");
    private final Label labelHost = new Label("New label");
    private final VerticalPanel verticalPanel_1 = new VerticalPanel();
    private final Label lblNewLabel_1 = new Label("labelAmountPlayers");
    private final TextArea textArea = new TextArea();
    private final VerticalPanel verticalPanel_2 = new VerticalPanel();
    private final TextBox textBox = new TextBox();
    private LobbyWidget lobbyWidget;

    public NetworkGameDetailsBitmapWidget(LobbyWidget lobbyWidget)
    {
        this();
        this.lobbyWidget = lobbyWidget;
    }

    public NetworkGameDetailsBitmapWidget()
    {
        dockPanel.setSpacing(5);
        initWidget(dockPanel);
        dockPanel.setSize("50em", "234px");

        dockPanel.add(verticalPanel, DockPanel.WEST);
        labelGameName.setStyleName("header-label");
        verticalPanel.add(labelGameName);
        labelGameName.setSize("20em", "2em");
        verticalPanel.add(lblBoard);
        lblBoard.setWidth("8em");
        verticalPanel.add(labelBoardName);
        labelBoardName.setSize("20em", "2em");
        verticalPanel.add(lblNewLabel);
        lblNewLabel.setWidth("8em");
        verticalPanel.add(labelHost);
        labelHost.setSize("20em", "2em");

        dockPanel.add(verticalPanel_1, DockPanel.EAST);

        verticalPanel_1.add(lblNewLabel_1);
        verticalPanel_1.add(listboxUsers);
        listboxUsers.setVisibleItemCount(8);
        listboxUsers.setSize("10em", "10em");
        verticalPanel_2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

        dockPanel.add(verticalPanel_2, DockPanel.CENTER);
        verticalPanel_2.add(textArea);
        textArea.setSize("20em", "20em");

        verticalPanel_2.add(textBox);
        textBox.setWidth("95%");
    }

    @Override
    public void setGame(GameInfo gameInfo)
    {
        this.gameInfo = gameInfo;
        if (gameInfo != null)
        {
            labelGameName.setText(gameInfo.getName());
            labelBoardName.setText(gameInfo.getBoardId());
        }
    }

}
