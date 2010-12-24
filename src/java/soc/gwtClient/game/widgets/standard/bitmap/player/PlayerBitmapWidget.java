package soc.gwtClient.game.widgets.standard.bitmap.player;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

import soc.common.game.Game;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractPlayerWidget;
import soc.gwtClient.game.abstractWidgets.IDevelopmentCardsAmountWidget;
import soc.gwtClient.game.abstractWidgets.ILargestArmyWidget;
import soc.gwtClient.game.abstractWidgets.ILongestRoadWidget;
import soc.gwtClient.game.abstractWidgets.IResourceAmountWidget;
import soc.gwtClient.game.abstractWidgets.IStockWidget;
import soc.gwtClient.game.abstractWidgets.IVictoryPointsWidget;
import soc.gwtClient.game.widgets.standard.bitmap.LargestArmyBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.LongestRoadBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.ResourceAmountBitmapWidget;

public class PlayerBitmapWidget extends AbstractPlayerWidget
{
    public PlayerBitmapWidget(Game game, Player player)
    {
        super(game, player);
    }

    @Override
    public IStockWidget createStockWidget()
    {
        return new StockBitmapWidget(game, player);
    }

    @Override
    public ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }
    @Override
    public IDevelopmentCardsAmountWidget createDevcardsWidget()
    {
        return new DevelopmentCardsAmountBitmapWidget(player);
    }

    @Override
    public ILargestArmyWidget createLargestArmyWidget()
    {
        return new LargestArmyBitmapWidget(player);
    }

    @Override
    public ILongestRoadWidget createLongestRoadWidget()
    {
        return new LongestRoadBitmapWidget(player);
    }

    @Override
    public IResourceAmountWidget createResourcesWidget()
    {
        return new ResourceAmountBitmapWidget(player);
    }

    @Override
    public IVictoryPointsWidget createVictoryPointWidget()
    {
        return new VictoryPointsBitmapWidget(player);
    }

}
