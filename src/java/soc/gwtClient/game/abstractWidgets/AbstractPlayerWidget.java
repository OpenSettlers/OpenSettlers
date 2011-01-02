package soc.gwtClient.game.abstractWidgets;

import soc.common.game.Game;
import soc.common.game.GamePlayer;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPlayerWidget implements IPlayerWidget
{
    protected IStockWidget stockWidget;
    protected IDevelopmentCardsAmountWidget devcardsWidget;
    protected ILargestArmyWidget largestArmyWidget;
    protected IResourceAmountWidget resourcesWidget;
    protected ILongestRoadWidget longestRoadWidget;
    protected IVictoryPointsWidget victoryPointWidget;

    protected ComplexPanel rootPanel;
    protected Game game;
    protected GamePlayer player;
    protected Label lblName = new Label();

    public AbstractPlayerWidget(Game game, GamePlayer player)
    {
        this.game = game;
        this.player = player;

        rootPanel = createRootPanel();

        stockWidget = createStockWidget();
        lblName.setText(player.getUser().getName());

        devcardsWidget = createDevcardsWidget();
        largestArmyWidget = createLargestArmyWidget();
        resourcesWidget = createResourcesWidget();
        longestRoadWidget = createLongestRoadWidget();
        victoryPointWidget = createVictoryPointWidget();

        rootPanel.add(lblName);
        rootPanel.add(stockWidget);

        rootPanel.add(devcardsWidget);
        rootPanel.add(resourcesWidget);
        rootPanel.add(largestArmyWidget);
        rootPanel.add(longestRoadWidget);
        rootPanel.add(victoryPointWidget);

        rootPanel.setStyleName("player-widget-" + player.getColor());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.gwtClient.client.game.IPlayerPanel#createRootPanel()
     */
    @Override
    public ComplexPanel createRootPanel()
    {
        return new VerticalPanel();
    }
}
