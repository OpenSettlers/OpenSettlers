package soc.gwtClient.lobby;

import soc.common.lobby.GameInfo;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GameSummaryBitmapWidget extends VerticalPanel implements
                GameSummaryWidget
{
    private Label labelBoardName;
    private Label labelGameName;
    private Label labelAmountPlayers;
    private GameInfo gameInfo;

    public GameSummaryBitmapWidget(GameInfo gameInfo)
    {
        this();
        this.gameInfo = gameInfo;
        labelGameName.setText(gameInfo.getName());
        labelBoardName.setText(gameInfo.getBoardId());
        labelAmountPlayers.setText("1");
    }

    public GameSummaryBitmapWidget()
    {
        setSize("100%", "5em");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        add(horizontalPanel);
        horizontalPanel.setWidth("100%");

        labelGameName = new Label("New label");
        labelGameName.setStyleName("header-label");
        horizontalPanel.add(labelGameName);
        labelGameName.setWidth("238px");

        labelAmountPlayers = new Label("New label");
        labelAmountPlayers
                        .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        horizontalPanel.add(labelAmountPlayers);

        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        horizontalPanel_1
                        .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        add(horizontalPanel_1);
        horizontalPanel_1.setWidth("100%");

        Image image = new Image(
                        "WEB-INF/classes/soc/gwtClient/images/defaultTheme/size32/City.png");
        horizontalPanel_1.add(image);
        image.setSize("32", "32");

        labelBoardName = new Label("BoardName");
        labelBoardName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        horizontalPanel_1.add(labelBoardName);
    }

    @Override
    public GameInfo getGameInfo()
    {
        return gameInfo;
    }

}
