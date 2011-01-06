package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.board.resources.Ore;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.Wheat;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.widgets.bitmap.TradeListBitmapWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class AbstractTradePlayerStatusWidget implements
        TradePlayerStatusWidget
{
    private GamePlayer opponent;
    private GamePlayer playingPlayer;
    private GamePanel gamePanel;
    private ComplexPanel rootPanel = new HorizontalPanel();
    private Image imgStatus = new Image();
    private PushButton btnAccept = new PushButton("Accept");
    private TradeListWidget tradeResources;

    public AbstractTradePlayerStatusWidget(GamePanel gamePanel,
            GamePlayer opponent, GamePlayer playingPlayer)
    {
        this.gamePanel = gamePanel;
        this.opponent = opponent;
        this.playingPlayer = playingPlayer;
        int height = gamePanel.getPlayersWidget().getPlayerWidgetHeight();
        rootPanel.setHeight(Integer.toString(height) + "px");

        ResourceList wantResources = new ResourceList();
        wantResources.add(new Wheat());

        ResourceList giveResources = new ResourceList();
        giveResources.add(new Ore());
        giveResources.add(new Ore());

        if (opponent != playingPlayer)
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
    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

}
