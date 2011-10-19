package org.soc.gwt.client.game.widgetsBitmap.dialogs;

import org.soc.common.core.GenericList.AddsList.ListAdded;
import org.soc.common.core.GenericList.ImmutableList;
import org.soc.common.core.GenericList.Models;
import org.soc.common.core.GenericList.RemovesList.ListRemoved;
import org.soc.common.game.DevelopmentCard.AbstractDevelopmentCard;
import org.soc.common.game.*;
import org.soc.common.game.Ports.PortList;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.MutableResourceListImpl;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.actions.GameBehaviour.TradeFirst;
import org.soc.common.game.pieces.Piece.PlayerPiece;
import org.soc.common.game.trading.*;
import org.soc.common.internationalization.*;
import org.soc.common.views.widgetsInterface.dialogs.*;
import org.soc.common.views.widgetsInterface.generic.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsBitmap.generic.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class TradeBankDialog extends PopupPanel implements BankTradeWidget {
  private MutableResourceList giveResources = new MutableResourceListImpl();
  private MutableResourceList wantResources = new MutableResourceListImpl();
  private MutableResourceList bankResources;
  private MutableResourceList playerHand;
  private PlayerPiece pieceToTradeFor;
  private GameWidget gameWidget;
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

  public TradeBankDialog(GameWidget gameWidget)
  {
    this();
    this.gameWidget = gameWidget;
    this.playerHand = gameWidget.playingPlayer().resources().copy();
    this.bankResources = gameWidget.game().bank().copy();
    this.player = gameWidget.playingPlayer();
    giveResourcesListWidget = createResourceListWidget(giveResources,
            playerHand, player.ports());
    wantedResourcesListWidget = createResourceListWidget(wantResources,
            bankResources, null);
    giveResourcesPickerWidget = createResourcePickerWidget(giveResources,
            player.ports(), playerHand, gameWidget);
    wantedResourcesPickerWidget = createResourcePickerWidget(wantResources,
            null, bankResources, gameWidget);
    givePanel.add(giveResourcesPickerWidget);
    givePanel.add(giveResourcesListWidget);
    needPanel.add(wantedResourcesPickerWidget);
    needPanel.add(wantedResourcesListWidget);
    giveResources.addListAddedHandler(new ListAdded<Resource>() {
      @Override public void listAdded(ImmutableList<Resource> items) {
        checkResources();
      }
    });
    wantResources.addListRemovedHandler(new ListRemoved<Resource>() {
      @Override public void listRemoved(ImmutableList<Resource> items) {
        checkResources();
      }
    });
  }
  private void checkResources()
  {
    // Player can trade when amount of gold resources equals amount of
    // picked resources
    btnTrade.setEnabled(gameWidget.playingPlayer().ports()
            .amountGold(giveResources) == wantResources.size()
            && wantResources.size() > 0);
  }
  private ResourceListWidget createResourceListWidget(MutableResourceList resources,
          MutableResourceList bankResources, PortList ports)
  {
    return new ResourceListBitmapWidget(resources, bankResources, ports);
  }
  private ResourcePickerWidget createResourcePickerWidget(
          ResourceList resources, PortList ports,
          ResourceList bankResources, GameWidget gameWidget)
  {
    return new ResourcePickerBitmapWidget(resources, ports, bankResources,
            gameWidget);
  }
  /** @return the pieceToTradeFor */
  public PlayerPiece getPieceToTradeFor()
  {
    return pieceToTradeFor;
  }
  /** @param pieceToTradeFor the pieceToTradeFor to set */
  public void setPieceToTradeFor(PlayerPiece pieceToTradeFor,
          TradeFirst tradeFirst)
  {
    this.pieceToTradeFor = pieceToTradeFor;
    this.tradeFirst = tradeFirst;
    this.setPlayer(gameWidget.playingPlayer());
    // Get rid of any old resources
    giveResources.clear();
    wantResources.clear();
    this.bankResources = gameWidget.game().bank().copy();
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
              .cost()));
      // Update image
      imgPiece.setUrl(Models.icon(pieceToTradeFor).iconDefault()
              .getURL());
      lblTradeForA.setText(I.get().ui().bankTradeForA() + " " +
              Models.name(pieceToTradeFor));
      panelPieceTrade.setVisible(true);
    } else
    {
      wantedResourcesListWidget.setEnabled(true);
      wantedResourcesPickerWidget.setEnabled(true);
      panelPieceTrade.setVisible(false);
    }
    show();
  }
  /** @wbp.parser.constructor */
  public TradeBankDialog()
  {
    VerticalPanel verticalPanel = new VerticalPanel();
    verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    setWidget(verticalPanel);
    HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
    horizontalPanel_2
            .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    horizontalPanel_2.setSpacing(10);
    verticalPanel.add(horizontalPanel_2);
    Image image = new Image(R.icons().bankTrade48());
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
    imgPiece = new Image(R.icons().city32());
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
        gameWidget.doAction(new TradeBank()
                .setOfferedResources(giveResources)
                .setWantedResources(wantResources)
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
  /** @return the player */
  public GamePlayer getPlayer()
  {
    return player;
  }
  /** @param player the player to set */
  public BankTradeWidget setPlayer(GamePlayer player)
  {
    this.player = player;
    playerHand = player.resources().copy();
    giveResourcesPickerWidget.setBankResources(playerHand);
    giveResourcesListWidget.setBankResources(playerHand);
    giveResourcesPickerWidget.setPorts(player.ports());
    return this;
  }
  /* TODO: ugly duplicate code. Consider using Buyable interface for playerpiece & devcard
   * 
   * @see org.soc.gwt.client.game.abstractWidgets.BankTradeUI#setDevcardTrade(org.soc.gwt.client
   * .game.behaviour.TradeFirst) */
  @Override public void setDevcardTrade(TradeFirst tradeFirst)
  {
    this.tradeFirst = tradeFirst;
    this.setPlayer(gameWidget.playingPlayer());
    // Get rid of any old resources
    giveResources.clear();
    wantResources.clear();
    this.bankResources = gameWidget.game().bank().copy();
    wantedResourcesPickerWidget.setBankResources(bankResources);
    wantedResourcesListWidget.setBankResources(bankResources);
    // Disable the wanted cards picker/list ui
    wantedResourcesListWidget.setEnabled(false);
    wantedResourcesPickerWidget.setEnabled(false);
    // Add needed resources
    wantResources.addList(playerHand
            .getNeededResources(AbstractDevelopmentCard.getCost()));
    // Update image
    imgPiece = new Image(R.icons().developmentCardBack32());
    lblTradeForA.setText(I.get().ui().bankTradeForA()
            + I.get().constants().developmentCard());
    panelPieceTrade.setVisible(true);
    show();
  }
  /* (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.PopupPanel#show() */
  @Override public void show()
  {
    Point2D location = gameWidget.getPlayersInfoWidget()
            .getTopRightLocation();
    setPopupPosition(location.getX(), location.getY());
    super.show();
  }
}
