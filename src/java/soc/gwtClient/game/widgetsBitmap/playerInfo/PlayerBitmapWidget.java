package soc.gwtClient.game.widgetsBitmap.playerInfo;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.generic.Point2D;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.payerInfo.DevelopmentCardsAmountWidget;
import soc.common.views.widgetsInterface.payerInfo.LargestArmyDetailWidget;
import soc.common.views.widgetsInterface.payerInfo.LongestRoadDetailWidget;
import soc.common.views.widgetsInterface.payerInfo.PlayerTurnStatusWidget;
import soc.common.views.widgetsInterface.payerInfo.PortAmountWidget;
import soc.common.views.widgetsInterface.payerInfo.ResourceAmountWidget;
import soc.common.views.widgetsInterface.payerInfo.StockWidget;
import soc.common.views.widgetsInterface.payerInfo.VictoryPointAmountWidget;
import soc.gwtClient.game.widgetsAbstract.playerInfo.AbstractPlayerWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class PlayerBitmapWidget extends AbstractPlayerWidget
{
    public PlayerBitmapWidget(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);
    }

    @Override
    public StockWidget createStockWidget()
    {
        return new StockBitmapWidget(gameWidget, player);
    }

    @Override
    public ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }

    @Override
    public DevelopmentCardsAmountWidget createDevcardsWidget()
    {
        return new DevelopmentCardsAmountBitmapWidget(player);
    }

    @Override
    public LargestArmyDetailWidget createLargestArmyWidget()
    {
        return new LargestArmyBitmapWidget(gameWidget, player);
    }

    @Override
    public LongestRoadDetailWidget createLongestRoadWidget()
    {
        return new LongestRoadBitmapWidget(player);
    }

    @Override
    public ResourceAmountWidget createResourcesWidget()
    {
        return new ResourceAmountBitmapWidget(player);
    }

    @Override
    public VictoryPointAmountWidget createVictoryPointWidget()
    {
        return new VictoryPointsBitmapWidget(gameWidget, player);
    }

    @Override
    public PlayerTurnStatusWidget createTurnStatusWidget()
    {
        return new PlayerTurnStatusBitmapWidget(player, gameWidget.getGame());
    }

    @Override
    public Point2D getTopRightLocation()
    {
        return new Point2D(getOffsetWidth() + getAbsoluteLeft(),
                getAbsoluteTop());
    }

    @Override
    public PortAmountWidget createPortAmountwidget()
    {
        return new PortAmountBitmapWidget(gameWidget, player);
    }

}
