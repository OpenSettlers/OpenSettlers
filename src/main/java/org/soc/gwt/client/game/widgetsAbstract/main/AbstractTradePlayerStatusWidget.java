package org.soc.gwt.client.game.widgetsAbstract.main;

import org.soc.common.core.GenericList.AddsList.ListAdded;
import org.soc.common.core.GenericList.ImmutableList;
import org.soc.common.core.GenericList.Models;
import org.soc.common.core.GenericList.RemovesList.ListRemoved;
import org.soc.common.game.*;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.MutableResourceListImpl;
import org.soc.common.game.actions.*;
import org.soc.common.game.trading.*;
import org.soc.common.views.widgetsInterface.dialogs.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsBitmap.main.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class AbstractTradePlayerStatusWidget implements
        TradePlayerStatusWidget, ClickHandler {
  private GamePlayer opponent;
  private GamePlayer playingPlayer;
  private GameWidget gameWidget;
  private ComplexPanel rootPanel = new HorizontalPanel();
  private Image imgStatus = new Image();
  private PushButton btnAccept = new PushButton("Accept");
  private TradeListWidget tradeResources;
  private TradePlayerDialog tradePlayerUI;
  private TradeResponse tradeResponse;

  public AbstractTradePlayerStatusWidget(GameWidget gameWidget,
          GamePlayer opponent, GamePlayer playingPlayer,
          TradePlayerDialog tradePlayerUI)
  {
    this.gameWidget = gameWidget;
    this.opponent = opponent;
    this.playingPlayer = playingPlayer;
    this.tradePlayerUI = tradePlayerUI;
    int height = gameWidget.getPlayersInfoWidget().getPlayerWidgetHeight();
    MutableResourceList wantResources = new MutableResourceListImpl();
    MutableResourceList giveResources = new MutableResourceListImpl();
    if (!opponent.equals(playingPlayer))
    {
      tradeResources = new TradeListBitmapWidget(wantResources,
              giveResources);
      imgStatus = new Image(R.icons().tradeDisabled32());
      rootPanel.add(imgStatus);
      rootPanel.add(tradeResources);
      rootPanel.add(btnAccept);
    } else
    {
      rootPanel.add(new Label("Mirror kitteh always offers versa :("));
    }
    btnAccept.addClickHandler(this);
    rootPanel.setHeight(Integer.toString(height) + "px");
    playingPlayer.resources().addListAddedHandler(new ListAdded<Resource>() {
      @Override public void listAdded(ImmutableList<Resource> items) {
        update();
      }
    });
    playingPlayer.resources().addListRemovedHandler(new ListRemoved<Resource>() {
      @Override public void listRemoved(ImmutableList<Resource> items) {
        update();
      }
    });
  }
  @Override public Widget asWidget() {
    return rootPanel;
  }
  @Override public void update(TradeResponse tradeResponse) {
    int height = gameWidget.getPlayersInfoWidget().getPlayerWidgetHeight();
    rootPanel.setHeight(Integer.toString(height) + "px");
    if (tradeResources != null) {
      if (tradeResponse != null) {
        this.tradeResponse = tradeResponse;
        if (tradeResponse.isAccepted())
          updateAccept((AcceptTradeOffer) tradeResponse);
        if (tradeResponse.isCounterOffer())
          updateCounter((CounterTradeOffer) tradeResponse);
        if (tradeResponse.isRejection())
          updateReject((RejectTradeOffer) tradeResponse);
      } else {
        btnAccept.setEnabled(false);
        btnAccept.setText("No offer");
        imgStatus.setUrl(R.icons().tradeDisabled32().getURL());
        tradeResources.getWantResources().clear();
        tradeResources.getGiveResources().clear();
      }
    }
  }
  @Override public void setWaiting()
  {
    btnAccept.setEnabled(false);
    btnAccept.setText("Waiting...");
    imgStatus.setUrl(R.icons().tradeWaiting32().getURL());
    if (tradeResources != null)
    {
      tradeResources.getWantResources().clear();
      tradeResources.getGiveResources().clear();
    }
  }
  @Override public void onClick(ClickEvent event)
  {
    tradePlayerUI.trade(tradeResponse);
    btnAccept.setEnabled(false);
  }
  private void updateCounter(CounterTradeOffer counter)
  {
    if (playingPlayer.resources().hasAtLeast(
            counter.getOfferedResources()))
    {
      btnAccept.setEnabled(true);
      btnAccept.setText("Accept counter");
    } else
    {
      btnAccept.setEnabled(false);
      btnAccept.setText("No resources");
    }
    imgStatus.setUrl(Models.mediumIcon(counter).getURL());
    tradeResources.getWantResources().clear();
    tradeResources.getWantResources().addList(
            counter.getRequestedResources());
    tradeResources.getGiveResources().clear();
    tradeResources.getGiveResources()
            .addList(counter.getOfferedResources());
  }
  private void updateReject(RejectTradeOffer reject)
  {
    btnAccept.setEnabled(false);
    btnAccept.setText("Rejected");
    imgStatus.setUrl(Models.mediumIcon(reject).getURL());
  }
  private void updateAccept(AcceptTradeOffer accept)
  {
    if (playingPlayer.resources().hasAtLeast(
            accept.getOriginatingOffer().getOfferedResources()))
    {
      btnAccept.setEnabled(true);
      btnAccept.setText("Accept");
    } else
    {
      btnAccept.setEnabled(false);
      btnAccept.setText("No resources");
    }
    imgStatus.setUrl(Models.mediumIcon(accept).getURL());
    tradeResources.getWantResources().clear();
    tradeResources.getWantResources().addList(
            tradeResponse.getOriginatingOffer()
                    .getRequestedResources());
    tradeResources.getGiveResources().clear();
    tradeResources.getGiveResources().addList(
            tradeResponse.getOriginatingOffer()
                    .getOfferedResources());
  }
  public void update()
  {
    if (tradeResponse != null) {
      if (tradeResponse.isCounterOffer())
        updateCounter((CounterTradeOffer) tradeResponse);
      if (tradeResponse.isAccepted())
        updateAccept((AcceptTradeOffer) tradeResponse);
    }
  }
}
