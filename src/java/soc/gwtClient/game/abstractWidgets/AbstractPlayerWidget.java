package soc.gwtClient.game.abstractWidgets;

import soc.common.game.Game;
import soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPlayerWidget extends HorizontalPanel implements
        PlayerWidget
{
    protected StockWidget stockWidget;
    protected DevelopmentCardsAmountWidget devcardsWidget;
    protected LargestArmyWidget largestArmyWidget;
    protected ResourceAmountWidget resourcesWidget;
    protected LongestRoadWidget longestRoadWidget;
    protected VictoryPointsWidget victoryPointWidget;
    protected PlayerTurnStatusWidget turnStatusWidget;
    protected PortAmountWidget portAmountWidget;

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
        portAmountWidget = createPortAmountwidget();

        add(lblName);
        add(stockWidget);
        add(portAmountWidget);

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
