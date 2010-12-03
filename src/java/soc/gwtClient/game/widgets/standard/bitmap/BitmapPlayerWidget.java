package soc.gwtClient.game.widgets.standard.bitmap;

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

public class BitmapPlayerWidget extends AbstractPlayerWidget
{
    public BitmapPlayerWidget(Game game, Player player)
    {
        super(game, player);
    }

    @Override
    public IStockWidget createStockWidget()
    {
        return new BitmapStockWidget(game, player);
    }

    @Override
    public ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }
    @Override
    public IDevelopmentCardsAmountWidget createDevcardsWidget()
    {
        return new BitmapDevelopmentCardsAmountWidget(player);
    }

    @Override
    public ILargestArmyWidget createLargestArmyWidget()
    {
        return new BitmapLargestArmyWidget(player);
    }

    @Override
    public ILongestRoadWidget createLongestRoadWidget()
    {
        return new BitmapLongestRoadWidget(player);
    }

    @Override
    public IResourceAmountWidget createResourcesWidget()
    {
        return new BitmapResourceAmountWidget(player);
    }

    @Override
    public IVictoryPointsWidget createVictoryPointWidget()
    {
        return new BitmapVictoryPointsWidget(player);
    }

}
