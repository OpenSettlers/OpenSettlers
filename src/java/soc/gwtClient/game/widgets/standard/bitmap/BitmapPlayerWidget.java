package soc.gwtClient.client.game.standard.bitmap;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

import soc.common.game.Game;
import soc.common.game.Player;
import soc.gwtClient.client.game.AbstractPlayerWidget;
import soc.gwtClient.client.game.IDevelopmentCardsAmountWidget;
import soc.gwtClient.client.game.ILargestArmyWidget;
import soc.gwtClient.client.game.ILongestRoadWidget;
import soc.gwtClient.client.game.IResourceAmountWidget;
import soc.gwtClient.client.game.IStockWidget;
import soc.gwtClient.client.game.IVictoryPointsWidget;

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
