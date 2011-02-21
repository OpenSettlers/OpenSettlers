package soc.gwtClient.game.widgetsBitmap.playerInfo;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.widgetsAbstract.playerInfo.AbstractPlayerWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.DevelopmentCardsAmountWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.LargestArmyDetailWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.LongestRoadDetailWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.PlayerTurnStatusWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.PortAmountWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.ResourceAmountWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.StockWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.VictoryPointAmountWidget;

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
