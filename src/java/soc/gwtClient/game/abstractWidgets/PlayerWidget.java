package soc.gwtClient.game.abstractWidgets;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface PlayerWidget extends IsWidget
{
    public ComplexPanel createRootPanel();

    public StockWidget createStockWidget();

    public DevelopmentCardsAmountWidget createDevcardsWidget();

    public LargestArmyWidget createLargestArmyWidget();

    public ResourceAmountWidget createResourcesWidget();

    public LongestRoadWidget createLongestRoadWidget();

    public VictoryPointsWidget createVictoryPointWidget();

    public PlayerTurnStatusWidget createTurnStatusWidget();

    public GamePlayer getPlayer();

    public Point2D getTopRightLocation();
}
