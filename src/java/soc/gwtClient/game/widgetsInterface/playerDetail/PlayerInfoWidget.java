package soc.gwtClient.game.widgetsInterface.playerDetail;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface PlayerInfoWidget extends IsWidget
{
    public ComplexPanel createRootPanel();

    public StockWidget createStockWidget();

    public DevelopmentCardsAmountWidget createDevcardsWidget();

    public LargestArmyDetailWidget createLargestArmyWidget();

    public ResourceAmountWidget createResourcesWidget();

    public LongestRoadDetailWidget createLongestRoadWidget();

    public VictoryPointAmountWidget createVictoryPointWidget();

    public PortAmountWidget createPortAmountwidget();

    public PlayerTurnStatusWidget createTurnStatusWidget();

    public GamePlayer getPlayer();

    public Point2D getTopRightLocation();
}
