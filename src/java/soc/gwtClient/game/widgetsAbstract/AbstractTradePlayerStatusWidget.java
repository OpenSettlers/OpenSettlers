package soc.gwtClient.game.widgetsAbstract;

import soc.common.actions.gameAction.turnActions.standard.AcceptTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.CounterTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.RejectTradeOffer;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.common.game.trading.TradeResponse;
import soc.gwtClient.game.widgetsBitmap.main.TradeListBitmapWidget;
import soc.gwtClient.game.widgetsInterface.dialogs.TradePlayerDialog;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.main.TradeListWidget;
import soc.gwtClient.game.widgetsInterface.main.TradePlayerStatusWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class AbstractTradePlayerStatusWidget implements
        TradePlayerStatusWidget, ClickHandler, ResourcesChangedEventHandler
{
    private GamePlayer opponent;
    private GamePlayer playingPlayer;
    private GameWidget gamePanel;
    private ComplexPanel rootPanel = new HorizontalPanel();
    private Image imgStatus = new Image();
    private PushButton btnAccept = new PushButton("Accept");
    private TradeListWidget tradeResources;
    private TradePlayerDialog tradePlayerUI;
    private TradeResponse tradeResponse;

    public AbstractTradePlayerStatusWidget(GameWidget gamePanel,
            GamePlayer opponent, GamePlayer playingPlayer,
            TradePlayerDialog tradePlayerUI)
    {
        this.gamePanel = gamePanel;
        this.opponent = opponent;
        this.playingPlayer = playingPlayer;
        this.tradePlayerUI = tradePlayerUI;
        int height = gamePanel.getPlayersInfoWidget().getPlayerWidgetHeight();

        ResourceList wantResources = new ResourceList();
        ResourceList giveResources = new ResourceList();

        if (!opponent.equals(playingPlayer))
        {
            tradeResources = new TradeListBitmapWidget(wantResources,
                    giveResources);
            imgStatus = new Image(Resources.icons().tradeDisabled());
            rootPanel.add(imgStatus);
            rootPanel.add(tradeResources);
            rootPanel.add(btnAccept);
        }
        else
        {
            rootPanel.add(new Label("Mirror kitteh always offers versa :("));
        }

        btnAccept.addClickHandler(this);

        rootPanel.setHeight(Integer.toString(height) + "px");

        playingPlayer.getResources().addResourcesChangedEventHandler(this);
    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.gwtClient.game.widgets.abstractWidgets.TradePlayerStatusWidget#update
     * (soc.common.game.trading.TradeResponse)
     */
    @Override
    public void update(TradeResponse tradeResponse)
    {
        int height = gamePanel.getPlayersInfoWidget().getPlayerWidgetHeight();
        rootPanel.setHeight(Integer.toString(height) + "px");
        if (tradeResources != null)
        {
            if (tradeResponse != null)
            {
                this.tradeResponse = tradeResponse;
                if (tradeResponse instanceof AcceptTradeOffer)
                    updateAccept((AcceptTradeOffer) tradeResponse);

                if (tradeResponse instanceof CounterTradeOffer)
                    updateCounter((CounterTradeOffer) tradeResponse);

                if (tradeResponse instanceof RejectTradeOffer)
                    updateReject((RejectTradeOffer) tradeResponse);
            }
            else
            {
                btnAccept.setEnabled(false);
                btnAccept.setText("No offer");
                imgStatus.setUrl(Resources.icons().tradeDisabled().getURL());
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
        imgStatus.setUrl(Resources.icons().tradeWaiting().getURL());
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
        }
        else
        {
            btnAccept.setEnabled(false);
            btnAccept.setText("No resources");
        }
        imgStatus.setUrl(Resources.icons().tradeCountered().getURL());

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
        imgStatus.setUrl(Resources.icons().tradeRejected().getURL());
    }

    private void updateAccept(AcceptTradeOffer accept)
    {
        if (playingPlayer.getResources().hasAtLeast(
                accept.getOriginatingOffer().getOfferedResources()))
        {
            btnAccept.setEnabled(true);
            btnAccept.setText("Accept");
        }
        else
        {
            btnAccept.setEnabled(false);
            btnAccept.setText("No resources");
        }
        imgStatus.setUrl(Resources.icons().tradeAccepted().getURL());

        tradeResources.getWantResources().clear();
        tradeResources.getWantResources().addList(
                tradeResponse.getOriginatingOffer().getRequestedResources());

        tradeResources.getGiveResources().clear();
        tradeResources.getGiveResources().addList(
                tradeResponse.getOriginatingOffer().getOfferedResources());
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        if (tradeResponse instanceof CounterTradeOffer)
            updateCounter((CounterTradeOffer) tradeResponse);

        if (tradeResponse instanceof AcceptTradeOffer)
            updateAccept((AcceptTradeOffer) tradeResponse);
    }
}
