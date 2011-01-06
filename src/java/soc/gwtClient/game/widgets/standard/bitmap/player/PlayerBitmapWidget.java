package soc.gwtClient.game.widgets.standard.bitmap.player;

import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractPlayerWidget;
import soc.gwtClient.game.abstractWidgets.DevelopmentCardsAmountWidget;
import soc.gwtClient.game.abstractWidgets.LargestArmyWidget;
import soc.gwtClient.game.abstractWidgets.LongestRoadWidget;
import soc.gwtClient.game.abstractWidgets.ResourceAmountWidget;
import soc.gwtClient.game.abstractWidgets.StockWidget;
import soc.gwtClient.game.abstractWidgets.VictoryPointsWidget;
import soc.gwtClient.game.abstractWidgets.PlayerTurnStatusWidget;
import soc.gwtClient.game.widgets.bitmap.PlayerTurnStatusBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.LargestArmyBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.LongestRoadBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.ResourceAmountBitmapWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class PlayerBitmapWidget extends AbstractPlayerWidget
{
    public PlayerBitmapWidget(Game game, GamePlayer player)
    {
        super(game, player);
    }

    @Override
    public StockWidget createStockWidget()
    {
        return new StockBitmapWidget(game, player);
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
        return new PlayerTurnStatusBitmapWidget(player, game);
    }

}
