package soc.gwtClient.game.abstractWidgets;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface IPlayerWidget extends IsWidget
{
    public ComplexPanel createRootPanel();
    public IStockWidget createStockWidget();
    public IDevelopmentCardsAmountWidget createDevcardsWidget();
    public ILargestArmyWidget createLargestArmyWidget();
    public IResourceAmountWidget createResourcesWidget();
    public ILongestRoadWidget createLongestRoadWidget();
    public IVictoryPointsWidget createVictoryPointWidget();
}
