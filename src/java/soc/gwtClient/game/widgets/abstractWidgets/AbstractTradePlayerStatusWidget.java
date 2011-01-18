package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.actions.gameAction.turnActions.standard.AcceptTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.CounterTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.RejectTradeOffer;
import soc.common.board.resources.Ore;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.Wheat;
import soc.common.game.player.GamePlayer;
import soc.common.game.trading.TradeResponse;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.widgets.bitmap.TradeListBitmapWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class AbstractTradePlayerStatusWidget implements TradePlayerStatusWidget
{
    private GamePlayer opponent;
    private GamePlayer playingPlayer;
    private GamePanel gamePanel;
    private ComplexPanel rootPanel = new HorizontalPanel();
    Image imgStatus = new Image();
    PushButton btnAccept = new PushButton("Accept");
    TradeListWidget tradeResources;

    public AbstractTradePlayerStatusWidget(GamePanel gamePanel,
            GamePlayer opponent, GamePlayer playingPlayer)
    {
        this.gamePanel = gamePanel;
        this.opponent = opponent;
        this.playingPlayer = playingPlayer;
        int height = gamePanel.getPlayersWidget().getPlayerWidgetHeight();

        ResourceList wantResources = new ResourceList();
        wantResources.add(new Wheat());

        ResourceList giveResources = new ResourceList();
        giveResources.add(new Ore());
        giveResources.add(new Ore());

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

        rootPanel.setHeight(Integer.toString(height) + "px");
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
        if (tradeResources != null)
        {
            if (tradeResponse != null)
            {
                if (tradeResponse instanceof AcceptTradeOffer)
                {
                    btnAccept.setEnabled(true);
                    btnAccept.setText("Accept");
                    imgStatus
                            .setUrl(Resources.icons().tradeAccepted().getURL());

                    tradeResources.getWantResources().clear();
                    tradeResources.getWantResources().add(
                            tradeResponse.getOriginatingOffer()
                                    .getRequestedResources());

                    tradeResources.getGiveResources().clear();
                    tradeResources.getGiveResources().add(
                            tradeResponse.getOriginatingOffer()
                                    .getOfferedResources());
                }

                if (tradeResponse instanceof CounterTradeOffer)
                {
                    btnAccept.setEnabled(true);
                    btnAccept.setText("Accept counter");
                    imgStatus.setUrl(Resources.icons().tradeCountered()
                            .getURL());

                    tradeResources.getWantResources().clear();
                    tradeResources.getWantResources().add(
                            ((CounterTradeOffer) tradeResponse)
                                    .getRequestedResources());

                    tradeResources.getGiveResources().clear();
                    tradeResources.getGiveResources().add(
                            ((CounterTradeOffer) tradeResponse)
                                    .getOfferedResources());
                }
                if (tradeResponse instanceof RejectTradeOffer)
                {
                    btnAccept.setEnabled(false);
                    btnAccept.setText("Rejected");
                    imgStatus
                            .setUrl(Resources.icons().tradeRejected().getURL());
                }
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

}
