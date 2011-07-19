package org.soc.gwt.client.game.widgetsAbstract.main;

import org.soc.common.actions.gameAction.standard.CounterTradeOffer;
import org.soc.common.actions.gameAction.trading.AcceptTradeOffer;
import org.soc.common.actions.gameAction.trading.RejectTradeOffer;
import org.soc.common.board.resources.ResourceList;
import org.soc.common.board.resources.ResourcesChangedEvent;
import org.soc.common.board.resources.ResourcesChangedEventHandler;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.game.trading.TradeResponse;
import org.soc.common.views.widgetsInterface.dialogs.TradePlayerDialog;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.TradeListWidget;
import org.soc.common.views.widgetsInterface.main.TradePlayerStatusWidget;
import org.soc.gwt.client.game.widgetsBitmap.main.TradeListBitmapWidget;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class AbstractTradePlayerStatusWidget implements
                TradePlayerStatusWidget, ClickHandler,
                ResourcesChangedEventHandler
{
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

        ResourceList wantResources = new ResourceList();
        ResourceList giveResources = new ResourceList();

        if (!opponent.equals(playingPlayer))
        {
            tradeResources = new TradeListBitmapWidget(wantResources,
                            giveResources);
            imgStatus = new Image(Resources.icons().tradeDisabled32());
            rootPanel.add(imgStatus);
            rootPanel.add(tradeResources);
            rootPanel.add(btnAccept);
        } else
        {
            rootPanel.add(new Label("Mirror kitteh always offers versa :("));
        }

        btnAccept.addClickHandler(this);

        rootPanel.setHeight(Integer.toString(height) + "px");

        playingPlayer.getResources().addResourcesChangedEventHandler(this);
    }

    /** @wbp.parser.entryPoint */
    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.gwt.client.game.widgets.abstractWidgets.TradePlayerStatusWidget#update
     * (org.soc.common.game.trading.TradeResponse)
     */
    @Override
    public void update(TradeResponse tradeResponse)
    {
        int height = gameWidget.getPlayersInfoWidget().getPlayerWidgetHeight();
        rootPanel.setHeight(Integer.toString(height) + "px");
        if (tradeResources != null)
        {
            if (tradeResponse != null)
            {
                this.tradeResponse = tradeResponse;
                if (tradeResponse.isAccepted())
                    updateAccept((AcceptTradeOffer) tradeResponse);

                if (tradeResponse.isCounterOffer())
                    updateCounter((CounterTradeOffer) tradeResponse);

                if (tradeResponse.isRejection())
                    updateReject((RejectTradeOffer) tradeResponse);
            } else
            {
                btnAccept.setEnabled(false);
                btnAccept.setText("No offer");
                imgStatus.setUrl(Resources.icons().tradeDisabled32().getURL());
                tradeResources.getWantResources().clear();
                tradeResources.getGiveResources().clear();
            }
        }
    }

    @Override
    public void setWaiting()
    {
        btnAccept.setEnabled(false);
        btnAccept.setText("Waiting...");
        imgStatus.setUrl(Resources.icons().tradeWaiting32().getURL());
        if (tradeResources != null)
        {
            tradeResources.getWantResources().clear();
            tradeResources.getGiveResources().clear();
        }
    }

    @Override
    public void onClick(ClickEvent event)
    {
        tradePlayerUI.trade(tradeResponse);
        btnAccept.setEnabled(false);
    }

    private void updateCounter(CounterTradeOffer counter)
    {
        if (playingPlayer.getResources().hasAtLeast(
                        counter.getOfferedResources()))
        {
            btnAccept.setEnabled(true);
            btnAccept.setText("Accept counter");
        } else
        {
            btnAccept.setEnabled(false);
            btnAccept.setText("No resources");
        }
        imgStatus.setUrl(Resources.mediumIcon(counter).getURL());

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
        imgStatus.setUrl(Resources.mediumIcon(reject).getURL());
    }

    private void updateAccept(AcceptTradeOffer accept)
    {
        if (playingPlayer.getResources().hasAtLeast(
                        accept.getOriginatingOffer().getOfferedResources()))
        {
            btnAccept.setEnabled(true);
            btnAccept.setText("Accept");
        } else
        {
            btnAccept.setEnabled(false);
            btnAccept.setText("No resources");
        }
        imgStatus.setUrl(Resources.mediumIcon(accept).getURL());

        tradeResources.getWantResources().clear();
        tradeResources.getWantResources().addList(
                        tradeResponse.getOriginatingOffer()
                                        .getRequestedResources());

        tradeResources.getGiveResources().clear();
        tradeResources.getGiveResources().addList(
                        tradeResponse.getOriginatingOffer()
                                        .getOfferedResources());
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        if (tradeResponse != null)
        {
            if (tradeResponse.isCounterOffer())
                updateCounter((CounterTradeOffer) tradeResponse);

            if (tradeResponse.isAccepted())
                updateAccept((AcceptTradeOffer) tradeResponse);
        }
    }
}
