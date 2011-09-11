package org.soc.gwt.client.game.widgetsAbstract.playerInfo;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.playerInfo.DevelopmentCardsAmountWidget;
import org.soc.common.views.widgetsInterface.playerInfo.LargestArmyDetailWidget;
import org.soc.common.views.widgetsInterface.playerInfo.LongestRoadDetailWidget;
import org.soc.common.views.widgetsInterface.playerInfo.PlayerInfoWidget;
import org.soc.common.views.widgetsInterface.playerInfo.PlayerTurnStatusWidget;
import org.soc.common.views.widgetsInterface.playerInfo.PortAmountWidget;
import org.soc.common.views.widgetsInterface.playerInfo.ResourceAmountWidget;
import org.soc.common.views.widgetsInterface.playerInfo.StockWidget;
import org.soc.common.views.widgetsInterface.playerInfo.VictoryPointAmountWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPlayerWidget extends HorizontalPanel implements
        PlayerInfoWidget
{
    protected StockWidget stockWidget;
    protected DevelopmentCardsAmountWidget devcardsWidget;
    protected LargestArmyDetailWidget largestArmyWidget;
    protected ResourceAmountWidget resourcesWidget;
    protected LongestRoadDetailWidget longestRoadWidget;
    protected VictoryPointAmountWidget victoryPointWidget;
    protected PlayerTurnStatusWidget turnStatusWidget;
    protected PortAmountWidget portAmountWidget;
    protected HorizontalPanel panelName = new HorizontalPanel();
    protected HorizontalPanel panelRest = new HorizontalPanel();

    protected GameWidget gameWidget;
    protected GamePlayer player;
    protected Label lblName = new Label();

    public AbstractPlayerWidget(GameWidget gameWidget, GamePlayer player)
    {
        this.gameWidget = gameWidget;
        this.player = player;

        stockWidget = createStockWidget();
        lblName.setText(player.user().name());

        devcardsWidget = createDevcardsWidget();
        largestArmyWidget = createLargestArmyWidget();
        resourcesWidget = createResourcesWidget();
        longestRoadWidget = createLongestRoadWidget();
        victoryPointWidget = createVictoryPointWidget();
        turnStatusWidget = createTurnStatusWidget();
        portAmountWidget = createPortAmountwidget();

        panelName.add(lblName);

        panelRest.add(stockWidget);
        panelRest.add(portAmountWidget);
        panelRest.add(devcardsWidget);
        panelRest.add(resourcesWidget);
        panelRest.add(largestArmyWidget);
        panelRest.add(longestRoadWidget);
        panelRest.add(victoryPointWidget);
        panelRest.add(turnStatusWidget);

        panelName.setHorizontalAlignment(ALIGN_LEFT);
        add(panelName);
        panelRest.setHorizontalAlignment(ALIGN_RIGHT);
        add(panelRest);

        this.setStyleName("player-widget-" + player.color());
        this.setWidth("100%");
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
     * @see org.soc.gwt.client.game.abstractWidgets.IPlayerWidget#getPlayer()
     */
    @Override
    public GamePlayer getPlayer()
    {
        return player;
    }
}
