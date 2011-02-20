package soc.gwtClient.game.widgetsBitmap.dialogs;

import soc.common.actions.gameAction.trading.TradeBank;
import soc.common.board.pieces.abstractPieces.PlayerPiece;
import soc.common.board.ports.PortList;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.behaviour.gameWidget.TradeFirst;
import soc.gwtClient.game.widgetsBitmap.generic.ResourceListBitmapWidget;
import soc.gwtClient.game.widgetsBitmap.generic.ResourcePickerBitmapWidget;
import soc.gwtClient.game.widgetsInterface.generic.ResourceListWidget;
import soc.gwtClient.game.widgetsInterface.generic.ResourcePickerWidget;
import soc.gwtClient.game.widgetsInterface.main.BankTradeWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TradeBankDialog extends PopupPanel implements BankTradeWidget,
        ResourcesChangedEventHandler
{
    private ResourceList giveResources = new ResourceList();
    private ResourceList wantResources = new ResourceList();
    private ResourceList bankResources;
    private ResourceList playerHand;
    private PlayerPiece pieceToTradeFor;
    private GameWidget gamePanel;
    private VerticalPanel needPanel;
    private VerticalPanel givePanel;
    private ResourceListWidget giveResourcesListWidget;
    private ResourceListWidget wantedResourcesListWidget;
    private ResourcePickerWidget giveResourcesPickerWidget;
    private ResourcePickerWidget wantedResourcesPickerWidget;
    private Button btnTrade;
    private Image imgPiece;
    private GamePlayer player;
    private TradeFirst tradeFirst;
    private HorizontalPanel panelPieceTrade;
    private Label lblTradeForA;

    public TradeBankDialog(GameWidget gamePanel)
    {
        this();
        this.gamePanel = gamePanel;
        this.playerHand = gamePanel.getPlayingPlayer().getResources().copy();
        this.bankResources = gamePanel.getGame().getBank().copy();
        this.player = gamePanel.getPlayingPlayer();

        giveResourcesListWidget = createResourceListWidget(giveResources,
                playerHand, player.getPorts());

        wantedResourcesListWidget = createResourceListWidget(wantResources,
                bankResources, null);

        giveResourcesPickerWidget = createResourcePickerWidget(giveResources,
                player.getPorts(), playerHand, gamePanel);

        wantedResourcesPickerWidget = createResourcePickerWidget(wantResources,
                null, bankResources, gamePanel);

        givePanel.add(giveResourcesPickerWidget);
        givePanel.add(giveResourcesListWidget);

        needPanel.add(wantedResourcesPickerWidget);
        needPanel.add(wantedResourcesListWidget);

        giveResources.addResourcesChangedEventHandler(this);
        wantResources.addResourcesChangedEventHandler(this);
    }

    private void checkResources()
    {
        // Player can trade when amount of gold resources equals amount of
        // picked resources
        btnTrade.setEnabled(gamePanel.getPlayingPlayer().getPorts().amountGold(
                giveResources) == wantResources.size()
                && wantResources.size() > 0);
    }

    private ResourceListWidget createResourceListWidget(ResourceList resources,
            ResourceList bankResources, PortList ports)
    {
        return new ResourceListBitmapWidget(resources, bankResources, ports);
    }

    private ResourcePickerWidget createResourcePickerWidget(
            ResourceList resources, PortList ports, ResourceList bankResources,
            GameWidget gamePanel)
    {
        return new ResourcePickerBitmapWidget(resources, ports, bankResources,
                gamePanel);
    }

    /**
     * @return the pieceToTradeFor
     */
    public PlayerPiece getPieceToTradeFor()
    {
        return pieceToTradeFor;
    }

    /**
     * @param pieceToTradeFor
     *            the pieceToTradeFor to set
     */
    public void setPieceToTradeFor(PlayerPiece pieceToTradeFor,
            TradeFirst tradeFirst)
    {
        this.pieceToTradeFor = pieceToTradeFor;
        this.tradeFirst = tradeFirst;
        this.setPlayer(gamePanel.getPlayingPlayer());

        // Get rid of any old resources
        giveResources.clear();
        wantResources.clear();

        this.bankResources = gamePanel.getGame().getBank().copy();

        wantedResourcesPickerWidget.setBankResources(bankResources);
        wantedResourcesListWidget.setBankResources(bankResources);

        // When trading for a particular piece, the player cannot choose
        // resources to trade for.
        // Instead, they are automatically determined
        if (pieceToTradeFor != null)
        {
            // Disable the wanted cards picker/list ui
            wantedResourcesListWidget.setEnabled(false);
            wantedResourcesPickerWidget.setEnabled(false);

            // Add needed resources
            wantResources.addList(playerHand.getNeededResources(pieceToTradeFor
                    .getCost()));

            // Update image
            imgPiece.setUrl(Resources.piece(pieceToTradeFor).getURL());
            lblTradeForA.setText(I18n.get().ui().bankTradeForA()
                    + I18n.piece(pieceToTradeFor));
            panelPieceTrade.setVisible(true);
        }
        else
        {
            wantedResourcesListWidget.setEnabled(true);
            wantedResourcesPickerWidget.setEnabled(true);
            panelPieceTrade.setVisible(false);
        }
        show();
    }

    /**
     * @wbp.parser.constructor
     */
    public TradeBankDialog()
    {
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel
                .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        setWidget(verticalPanel);

        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        horizontalPanel_2
                .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horizontalPanel_2.setSpacing(10);
        verticalPanel.add(horizontalPanel_2);

        Image image = new Image(Resources.icons().bankTrade());
        horizontalPanel_2.add(image);
        image.setSize("140", "140");

        Label lblTradeWithThe = new Label("Trade with the bank");
        lblTradeWithThe.setStyleName("label-title");
        horizontalPanel_2.add(lblTradeWithThe);
        lblTradeWithThe.setWidth("227px");

        panelPieceTrade = new HorizontalPanel();
        panelPieceTrade.setSpacing(10);
        horizontalPanel_2.add(panelPieceTrade);
        panelPieceTrade.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

        imgPiece = new Image(Resources.icons().city());
        panelPieceTrade.add(imgPiece);
        imgPiece.setSize("48", "48");

        lblTradeForA = new Label("Trade for a city");
        panelPieceTrade.add(lblTradeForA);

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setSpacing(10);
        verticalPanel.add(horizontalPanel);

        needPanel = new VerticalPanel();
        horizontalPanel.add(needPanel);

        Label lblWhatYouNeed = new Label("get");
        lblWhatYouNeed.setStyleName("label-title");
        needPanel.add(lblWhatYouNeed);

        givePanel = new VerticalPanel();
        horizontalPanel.add(givePanel);

        Label lblWhatYouWant = new Label("give");
        lblWhatYouWant.setStyleName("label-title");
        givePanel.add(lblWhatYouWant);

        HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
        horizontalPanel_4
                .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horizontalPanel_4.setSpacing(10);
        verticalPanel.add(horizontalPanel_4);

        Button button = new Button("New button");
        button.setStyleName("cancel-button");
        button.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent arg0)
            {
                hide();
            }
        });
        button.setText("Cancel");
        horizontalPanel_4.add(button);

        btnTrade = new Button("New button");
        btnTrade.setStyleName("ok-button");
        btnTrade.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent arg0)
            {
                gamePanel.sendAction(new TradeBank().setOfferedResources(
                        giveResources).setWantedResources(wantResources)
                        .setPlayer(player));
                if (tradeFirst != null)
                {
                    tradeFirst.onTraded();
                }
                hide();
            }
        });
        btnTrade.setText("Trade!");
        horizontalPanel_4.add(btnTrade);
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        checkResources();
    }

    /**
     * @return the player
     */
    public GamePlayer getPlayer()
    {
        return player;
    }

    /**
     * @param player
     *            the player to set
     */
    public BankTradeWidget setPlayer(GamePlayer player)
    {
        this.player = player;

        playerHand = player.getResources().copy();
        giveResourcesPickerWidget.setBankResources(playerHand);
        giveResourcesListWidget.setBankResources(playerHand);
        giveResourcesPickerWidget.setPorts(player.getPorts());

        return this;
    }

    /*
     * TODO: ugly duplicate code. Consider using Buyable interface for
     * playerpiece & devcard
     * 
     * @see
     * soc.gwtClient.game.abstractWidgets.BankTradeUI#setDevcardTrade(soc.gwtClient
     * .game.behaviour.TradeFirst)
     */
    @Override
    public void setDevcardTrade(TradeFirst tradeFirst)
    {
        this.tradeFirst = tradeFirst;
        this.setPlayer(gamePanel.getPlayingPlayer());

        // Get rid of any old resources
        giveResources.clear();
        wantResources.clear();

        this.bankResources = gamePanel.getGame().getBank().copy();

        wantedResourcesPickerWidget.setBankResources(bankResources);
        wantedResourcesListWidget.setBankResources(bankResources);

        // Disable the wanted cards picker/list ui
        wantedResourcesListWidget.setEnabled(false);
        wantedResourcesPickerWidget.setEnabled(false);

        // Add needed resources
        wantResources.addList(playerHand.getNeededResources(DevelopmentCard
                .getCost()));

        // Update image
        imgPiece = new Image(Resources.icons().developmentCardBack());
        lblTradeForA.setText(I18n.get().ui().bankTradeForA()
                + I18n.get().constants().developmentCard());
        panelPieceTrade.setVisible(true);

        show();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.PopupPanel#show()
     */
    @Override
    public void show()
    {
        Point2D location = gamePanel.getPlayersInfoWidget().getTopRightLocation();
        setPopupPosition(location.getX(), location.getY());

        super.show();
    }

}
