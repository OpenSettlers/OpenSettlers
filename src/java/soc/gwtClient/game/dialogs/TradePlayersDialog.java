package soc.gwtClient.game.dialogs;

import java.util.HashMap;
import java.util.Map;

import soc.common.actions.gameAction.turnActions.standard.TradeOffer;
import soc.common.board.ports.PortList;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.TurnChangedEvent;
import soc.common.game.TurnChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.common.game.player.OrderChangedEvent;
import soc.common.game.player.OrderChangedEventHandler;
import soc.common.game.trading.TradeOfferedEvent;
import soc.common.game.trading.TradeOfferedEventHandler;
import soc.common.game.trading.TradeRespondedEvent;
import soc.common.game.trading.TradeRespondedEventHandler;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.TradePlayerUI;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceListWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourcePickerWidget;
import soc.gwtClient.game.widgets.abstractWidgets.TradePlayerStatusWidget;
import soc.gwtClient.game.widgets.bitmap.ResourceListBitmapWidget;
import soc.gwtClient.game.widgets.bitmap.ResourcePickerBitmapWidget;
import soc.gwtClient.game.widgets.bitmap.TradePlayerStatusBitmapWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TradePlayersDialog extends PopupPanel implements TradePlayerUI,
        TurnChangedEventHandler, TradeOfferedEventHandler,
        OrderChangedEventHandler, TradeRespondedEventHandler,
        ResourcesChangedEventHandler
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
    private Map<GamePlayer, TradePlayerStatusWidget> playerStatuses = new HashMap<GamePlayer, TradePlayerStatusWidget>();
    private VerticalPanel pnlTradeStatuses;
    private GamePlayer player;
    private HandlerRegistration tradeOfferHandler;
    private HandlerRegistration tradeResponseHandler;
    private Button btnOfferTrade;
    private Label lblOfferStatus;

    public TradePlayersDialog(GamePanel gamePanel, GamePlayer player)
    {
        this();
        this.gamePanel = gamePanel;
        this.player = player;

        this.playerHand = player.getResources().copy();

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
                    gamePanel, opponent, player);
            playerStatuses.put(opponent, tradeStatus);
            pnlTradeStatuses.add(tradeStatus);
        }

        pnlGiveResources.add(giveResourcePickerWidget);
        pnlGiveResources.add(giveResourcesListWidget);

        pnlWantResources.add(wantedResourcePickerWidget);
        pnlWantResources.add(wantedResourceListWidget);

        gamePanel.getGame().addTurnChangedEventHandler(this);
        gamePanel.getGame().getPlayers().addOrderChangedEventHandler(this);
        wantResources.addResourcesChangedEventHandler(this);
        giveResources.addResourcesChangedEventHandler(this);
    }

    private ResourceListWidget createResourceListWidget(ResourceList resources,
            ResourceList bankResources, PortList ports)
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

    public void setPlayer(GamePlayer player)
    {
        this.player = player;

        this.playerHand = player.getResources().copy();
        giveResourcePickerWidget.setBankResources(playerHand);
        giveResourcePickerWidget.setPorts(player.getPorts());
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

        lblOfferStatus = new Label("New label");
        horizontalPanel_3.add(lblOfferStatus);

        btnOfferTrade = new Button("New button");
        btnOfferTrade.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                offerTrade();
            }
        });
        btnOfferTrade.setText("Offer trade");
        horizontalPanel_3.add(btnOfferTrade);

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
        horizontalPanel_1.setHeight("156px");
        pnlTradeOptions.add(horizontalPanel_1);

        pnlWantResources = new VerticalPanel();
        pnlWantResources.setHeight("145px");
        pnlWantResources.setSpacing(10);
        horizontalPanel_1.add(pnlWantResources);

        Label lblResourcesYouHave = new Label(
                "Resources you have dire need for");
        pnlWantResources.add(lblResourcesYouHave);

        pnlGiveResources = new VerticalPanel();
        pnlGiveResources.setHeight("145px");
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

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.PopupPanel#show()
     */
    @Override
    public void show()
    {
        updateResources();

        for (TradePlayerStatusWidget tradeStatus : playerStatuses.values())
            tradeStatus.update(null);

        Point2D location = gamePanel.getPlayersWidget().getTopRightLocation();
        setPopupPosition(location.getX(), location.getY());
        super.show();
    }

    private void offerTrade()
    {
        TradeOffer offer = new TradeOffer();
        offer.setPlayer(player);
        offer.getOfferedResources().add(giveResources);
        offer.getRequestedResources().add(wantResources);
        gamePanel.sendAction(offer);
    }

    private void updateResources()
    {
        // Get rid of any old resources
        giveResources.clear();
        wantResources.clear();

        playerHand = player.getResources().copy();
        giveResourcePickerWidget.setBankResources(playerHand);
    }

    @Override
    public void onTurnChanged(TurnChangedEvent event)
    {
        if (tradeOfferHandler != null)
        {
            tradeOfferHandler.removeHandler();
            tradeOfferHandler = null;
        }

        if (player.isOnTurn())
        {
            tradeOfferHandler = gamePanel.getGame().getCurrentTurn()
                    .getTradeOffers().addTradeOfferedEventHandler(this);
        }
    }

    @Override
    public void onTradeOffered(TradeOfferedEvent event)
    {
        btnOfferTrade.setEnabled(false);

        if (tradeResponseHandler != null)
        {
            tradeResponseHandler.removeHandler();
            tradeResponseHandler = null;
        }

        tradeResponseHandler = gamePanel.getGame().getCurrentTurn()
                .getTradeOffers().getLatestOffer().getResponses()
                .addTradeRespondedEventHandler(this);

        for (TradePlayerStatusWidget tradeStatus : playerStatuses.values())
            tradeStatus.setWaiting();
    }

    @Override
    public void onTradeResponded(TradeRespondedEvent event)
    {
        TradePlayerStatusWidget widget = playerStatuses.get(event
                .getTradeResponse().getPlayer());
        widget.update(event.getTradeResponse());
        checkUI();
    }

    /*
     * Clears the list of trade statuses, and adds all trade statuses back to
     * the containing panel. This ensures the order of players is updated
     * according to the list of players in the game.
     * 
     * @see
     * soc.common.game.player.OrderChangedEventHandler#onOrderChanged(soc.common
     * .game.player.OrderChangedEvent)
     */
    @Override
    public void onOrderChanged(OrderChangedEvent event)
    {
        pnlTradeStatuses.clear();

        for (GamePlayer player : gamePanel.getGame().getPlayers())
            pnlTradeStatuses.add(playerStatuses.get(player));
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        checkUI();
    }

    private void checkUI()
    {
        if (wantResources.size() > 0 && giveResources.size() > 0)
        {
            if (gamePanel.getGame().getCurrentTurn().getTradeOffers()
                    .getLatestOffer() != null)
            {
                if (gamePanel.getGame().getCurrentTurn().getTradeOffers()
                        .getLatestOffer().isResponsesCompleted())
                {
                    btnOfferTrade.setEnabled(true);
                }
                else
                {
                    btnOfferTrade.setEnabled(false);
                }
            }
            else
            {
                btnOfferTrade.setEnabled(true);
            }
        }
        else
        {
            btnOfferTrade.setEnabled(false);
        }
    }
}