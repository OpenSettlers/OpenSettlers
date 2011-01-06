package soc.gwtClient.game.dialogs;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.ports.PortList;
import soc.common.board.resources.ResourceList;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceListWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourcePickerWidget;
import soc.gwtClient.game.widgets.abstractWidgets.TradePlayerStatusWidget;
import soc.gwtClient.game.widgets.bitmap.ResourceListBitmapWidget;
import soc.gwtClient.game.widgets.bitmap.ResourcePickerBitmapWidget;
import soc.gwtClient.game.widgets.bitmap.TradePlayerStatusBitmapWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TradePlayersDialog extends PopupPanel
{
    private ResourceList wantResources = new ResourceList();
    private ResourceList giveResources = new ResourceList();
    private ResourceList playerHand;
    private ResourcePickerWidget giveResourcePickerWidget;
    private ResourcePickerWidget wantedResourcePickerWidget;
    private ResourceListWidget giveResourcesListWidget;
    private ResourceListWidget wantedResourceListWidget;
    private GamePanel gamePanel;
    private VerticalPanel pnlGiveResources;
    private VerticalPanel pnlWantResources;
    private List<TradePlayerStatusWidget> playerStatuses = new ArrayList<TradePlayerStatusWidget>();
    private VerticalPanel pnlTradeStatuses;

    public TradePlayersDialog(GamePanel gamePanel)
    {
        this();

        this.gamePanel = gamePanel;
        this.playerHand = gamePanel.getPlayingPlayer().getResources().copy();

        giveResourcesListWidget = createResourceListWidget(giveResources,
                playerHand, null);

        wantedResourceListWidget = createResourceListWidget(wantResources,
                null, null);

        giveResourcePickerWidget = createResourcePickerWidget(giveResources,
                null, playerHand, gamePanel);

        wantedResourcePickerWidget = createResourcePickerWidget(wantResources,
                null, null, gamePanel);

        for (GamePlayer opponent : gamePanel.getGame().getPlayers())
        {
            TradePlayerStatusWidget tradeStatus = new TradePlayerStatusBitmapWidget(
                    gamePanel, opponent, gamePanel.getPlayingPlayer());
            playerStatuses.add(tradeStatus);
            pnlTradeStatuses.add(tradeStatus);
        }

        pnlGiveResources.add(giveResourcePickerWidget);
        pnlGiveResources.add(giveResourcesListWidget);

        pnlWantResources.add(wantedResourcePickerWidget);
        pnlWantResources.add(wantedResourceListWidget);
    }

    private ResourceListWidget createResourceListWidget(
            ResourceList resources, ResourceList bankResources, PortList ports)
    {
        return new ResourceListBitmapWidget(resources, bankResources, ports);
    }

    private ResourcePickerWidget createResourcePickerWidget(
            ResourceList resources, PortList ports, ResourceList bankResources,
            GamePanel gamePanel)
    {
        return new ResourcePickerBitmapWidget(resources, ports, bankResources,
                gamePanel);
    }

    /**
     * @wbp.parser.constructor
     */
    public TradePlayersDialog()
    {
        super(true);

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        setWidget(horizontalPanel);
        horizontalPanel.setSize("521px", "339px");

        VerticalPanel pnlPlayersResponse = new VerticalPanel();
        pnlPlayersResponse
                .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        horizontalPanel.add(pnlPlayersResponse);

        pnlTradeStatuses = new VerticalPanel();
        pnlPlayersResponse.add(pnlTradeStatuses);

        HorizontalPanel horizontalPanel_3 = new HorizontalPanel();
        horizontalPanel_3.setSpacing(10);
        pnlPlayersResponse.add(horizontalPanel_3);

        Button button_1 = new Button("New button");
        button_1.setText("Offer trade");
        horizontalPanel_3.add(button_1);

        VerticalPanel pnlTradeOptions = new VerticalPanel();
        horizontalPanel.add(pnlTradeOptions);

        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        horizontalPanel_2
                .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horizontalPanel_2.setSpacing(10);
        pnlTradeOptions.add(horizontalPanel_2);

        Image image = new Image(Resources.icons().tradePlayer());
        horizontalPanel_2.add(image);

        Label lblTradeWithOpponents = new Label("Trade with opponents");
        horizontalPanel_2.add(lblTradeWithOpponents);

        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        pnlTradeOptions.add(horizontalPanel_1);

        pnlWantResources = new VerticalPanel();
        pnlWantResources.setSpacing(10);
        horizontalPanel_1.add(pnlWantResources);

        Label lblResourcesYouHave = new Label(
                "Resources you have dire need for");
        pnlWantResources.add(lblResourcesYouHave);

        pnlGiveResources = new VerticalPanel();
        pnlGiveResources.setSpacing(10);
        horizontalPanel_1.add(pnlGiveResources);

        Label lblResourcesYouCan = new Label("Resources you can get rid of");
        pnlGiveResources.add(lblResourcesYouCan);

        DockPanel dockPanel = new DockPanel();
        dockPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        dockPanel.setSpacing(10);
        pnlTradeOptions.add(dockPanel);

        HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
        dockPanel.add(horizontalPanel_4, DockPanel.WEST);

        Label lblYouHave = new Label("You have ");
        horizontalPanel_4.add(lblYouHave);
        lblYouHave.setWidth("67px");

        Label lblX = new Label("X");
        horizontalPanel_4.add(lblX);
        lblX.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        lblX.setWidth("22px");

        Label lblTradesLeft = new Label("trades left");
        horizontalPanel_4.add(lblTradesLeft);
        lblTradesLeft.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

        Button button = new Button("New button");
        dockPanel.add(button, DockPanel.EAST);
        button.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent arg0)
            {
                hide();
            }
        });
        button.setText("Close");
        button.setWidth("101px");
    }

}
