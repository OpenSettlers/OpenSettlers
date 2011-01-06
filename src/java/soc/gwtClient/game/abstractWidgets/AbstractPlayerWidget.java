package soc.gwtClient.game.abstractWidgets;

import soc.common.game.Game;
import soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPlayerWidget extends HorizontalPanel implements
        IPlayerWidget
{
    protected IStockWidget stockWidget;
    protected IDevelopmentCardsAmountWidget devcardsWidget;
    protected ILargestArmyWidget largestArmyWidget;
    protected IResourceAmountWidget resourcesWidget;
    protected ILongestRoadWidget longestRoadWidget;
    protected IVictoryPointsWidget victoryPointWidget;
    protected PlayerTurnStatusWidget turnStatusWidget;

    protected Game game;
    protected GamePlayer player;
    protected Label lblName = new Label();

    public AbstractPlayerWidget(Game game, GamePlayer player)
    {
        this.game = game;
        this.player = player;

        stockWidget = createStockWidget();
        lblName.setText(player.getUser().getName());

        devcardsWidget = createDevcardsWidget();
        largestArmyWidget = createLargestArmyWidget();
        resourcesWidget = createResourcesWidget();
        longestRoadWidget = createLongestRoadWidget();
        victoryPointWidget = createVictoryPointWidget();
        turnStatusWidget = createTurnStatusWidget();

        add(lblName);
        add(stockWidget);

        add(devcardsWidget);
        add(resourcesWidget);
        add(largestArmyWidget);
        add(longestRoadWidget);
        add(victoryPointWidget);
        add(turnStatusWidget);

        this.setStyleName("player-widget-" + player.getColor());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget()
    {
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.gwtClient.game.abstractWidgets.IPlayerWidget#getPlayer()
     */
    @Override
    public GamePlayer getPlayer()
    {
        return player;
    }
}
