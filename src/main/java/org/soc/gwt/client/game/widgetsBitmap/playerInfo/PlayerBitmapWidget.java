package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.playerInfo.DevelopmentCardsAmountWidget;
import org.soc.common.views.widgetsInterface.playerInfo.LargestArmyDetailWidget;
import org.soc.common.views.widgetsInterface.playerInfo.LongestRoadDetailWidget;
import org.soc.common.views.widgetsInterface.playerInfo.PlayerTurnStatusWidget;
import org.soc.common.views.widgetsInterface.playerInfo.PortAmountWidget;
import org.soc.common.views.widgetsInterface.playerInfo.ResourceAmountWidget;
import org.soc.common.views.widgetsInterface.playerInfo.StockWidget;
import org.soc.common.views.widgetsInterface.playerInfo.VictoryPointAmountWidget;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractPlayerWidget;

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
        return new PlayerTurnStatusBitmapWidget(player, gameWidget.game());
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
