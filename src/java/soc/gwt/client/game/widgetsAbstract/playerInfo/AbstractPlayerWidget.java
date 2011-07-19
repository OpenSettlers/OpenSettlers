package soc.gwt.client.game.widgetsAbstract.playerInfo;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.payerInfo.DevelopmentCardsAmountWidget;
import soc.common.views.widgetsInterface.payerInfo.LargestArmyDetailWidget;
import soc.common.views.widgetsInterface.payerInfo.LongestRoadDetailWidget;
import soc.common.views.widgetsInterface.payerInfo.PlayerInfoWidget;
import soc.common.views.widgetsInterface.payerInfo.PlayerTurnStatusWidget;
import soc.common.views.widgetsInterface.payerInfo.PortAmountWidget;
import soc.common.views.widgetsInterface.payerInfo.ResourceAmountWidget;
import soc.common.views.widgetsInterface.payerInfo.StockWidget;
import soc.common.views.widgetsInterface.payerInfo.VictoryPointAmountWidget;

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
        lblName.setText(player.getUser().getName());

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

        this.setStyleName("player-widget-" + player.getColor());
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
     * @see soc.gwt.client.game.abstractWidgets.IPlayerWidget#getPlayer()
     */
    @Override
    public GamePlayer getPlayer()
    {
        return player;
    }
}
