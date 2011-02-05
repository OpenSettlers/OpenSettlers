package soc.gwtClient.game.widgets.standard.bitmap.player;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.AbstractPlayerWidget;
import soc.gwtClient.game.abstractWidgets.DevelopmentCardsAmountWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.LargestArmyWidget;
import soc.gwtClient.game.abstractWidgets.LongestRoadWidget;
import soc.gwtClient.game.abstractWidgets.PlayerTurnStatusWidget;
import soc.gwtClient.game.abstractWidgets.PortAmountWidget;
import soc.gwtClient.game.abstractWidgets.ResourceAmountWidget;
import soc.gwtClient.game.abstractWidgets.StockWidget;
import soc.gwtClient.game.abstractWidgets.VictoryPointsWidget;
import soc.gwtClient.game.widgets.bitmap.PlayerTurnStatusBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.LargestArmyBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.LongestRoadBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.ResourceAmountBitmapWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class PlayerBitmapWidget extends AbstractPlayerWidget
{
    public PlayerBitmapWidget(GamePanel gamePanel, GamePlayer player)
    {
        super(gamePanel, player);
    }

    @Override
    public StockWidget createStockWidget()
    {
        return new StockBitmapWidget(gamePanel, player);
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
    public LargestArmyWidget createLargestArmyWidget()
    {
        return new LargestArmyBitmapWidget(player);
    }

    @Override
    public LongestRoadWidget createLongestRoadWidget()
    {
        return new LongestRoadBitmapWidget(player);
    }

    @Override
    public ResourceAmountWidget createResourcesWidget()
    {
        return new ResourceAmountBitmapWidget(player);
    }

    @Override
    public VictoryPointsWidget createVictoryPointWidget()
    {
        return new VictoryPointsBitmapWidget(player);
    }

    @Override
    public PlayerTurnStatusWidget createTurnStatusWidget()
    {
        return new PlayerTurnStatusBitmapWidget(player, gamePanel.getGame());
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
        return new PortAmountBitmapWidget(player);
    }

}
