package soc.gwtClient.game.dialogs;

import soc.common.actions.gameAction.turnActions.standard.TradeBank;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.ports.PortList;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.BankTradeUI;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.behaviour.TradeFirst;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceListWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourcePickerWidget;
import soc.gwtClient.game.widgets.bitmap.ResourceListBitmapWidget;
import soc.gwtClient.game.widgets.bitmap.ResourcePickerBitmapWidget;
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

public class TradeBankDialog extends PopupPanel implements BankTradeUI,
        ResourcesChangedEventHandler
{
    private ResourceList giveResources = new ResourceList();
    private ResourceList wantResources = new ResourceList();
    private ResourceList bankResources;
    private ResourceList playerHand;
    private PlayerPiece pieceToTradeFor;
    private GamePanel gamePanel;
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

    public TradeBankDialog(GamePanel gamePanel)
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
        btnTrade.setEnabled(gamePanel.getPlayingPlayer().amountGold(
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
            GamePanel gamePanel)
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
            wantResources.add(playerHand.getNeededResources(pieceToTradeFor
                    .getCost()));

            // Update image
            imgPiece.setUrl(Resources.piece(pieceToTradeFor).getURL());
            imgPiece.setVisible(true);
        }
        else
        {
            wantedResourcesListWidget.setEnabled(true);
            wantedResourcesPickerWidget.setEnabled(true);
            imgPiece.setVisible(false);
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
        verticalPanel.setSize("525px", "423px");

        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        horizontalPanel_2.setSpacing(10);
        verticalPanel.add(horizontalPanel_2);

        Label lblTradeWithThe = new Label("Trade with the bank");
        horizontalPanel_2.add(lblTradeWithThe);

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setSpacing(10);
        verticalPanel.add(horizontalPanel);

        needPanel = new VerticalPanel();
        horizontalPanel.add(needPanel);

        Label lblWhatYouNeed = new Label("What you need");
        needPanel.add(lblWhatYouNeed);

        givePanel = new VerticalPanel();
        horizontalPanel.add(givePanel);

        Label lblWhatYouWant = new Label("What you want");
        givePanel.add(lblWhatYouWant);

        HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
        horizontalPanel_4.setSpacing(10);
        verticalPanel.add(horizontalPanel_4);

        Button button = new Button("New button");
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

        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        horizontalPanel_1
                .setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
        horizontalPanel_1.setSpacing(10);
        verticalPanel.add(horizontalPanel_1);
        horizontalPanel_1.setSize("262px", "115px");

        Image image = new Image(Resources.icons().bankTrade());
        horizontalPanel_1.add(image);
        image.setSize("140", "140");

        HorizontalPanel horizontalPanel_3 = new HorizontalPanel();
        horizontalPanel_3
                .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horizontalPanel_1.add(horizontalPanel_3);

        imgPiece = new Image(Resources.icons().city());
        horizontalPanel_3.add(imgPiece);
        imgPiece.setSize("48", "48");

        Label lblTradeForA = new Label("Trade for a city");
        horizontalPanel_3.add(lblTradeForA);
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
    public BankTradeUI setPlayer(GamePlayer player)
    {
        this.player = player;

        this.playerHand = player.getResources().copy();
        giveResourcesPickerWidget.setBankResources(playerHand);
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
        wantResources.add(playerHand.getNeededResources(DevelopmentCard
                .getCost()));

        // Update image
        imgPiece = new Image(Resources.icons().developmentCardBack());
        imgPiece.setVisible(true);

        show();
    }
}
