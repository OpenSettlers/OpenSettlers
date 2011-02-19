package soc.gwtClient.game.widgetsAbstract;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.DevelopmentCardsAmountWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.LargestArmyDetailWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.LongestRoadDetailWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.PlayerTurnStatusWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.PlayerInfoWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.PortAmountWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.ResourceAmountWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.StockWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.VictoryPointAmountWidget;

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

    protected GameWidget gamePanel;
    protected GamePlayer player;
    protected Label lblName = new Label();

    public AbstractPlayerWidget(GameWidget gamePanel, GamePlayer player)
    {
        this.gamePanel = gamePanel;
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
     * @see soc.gwtClient.game.abstractWidgets.IPlayerWidget#getPlayer()
     */
    @Override
    public GamePlayer getPlayer()
    {
        return player;
    }
}
