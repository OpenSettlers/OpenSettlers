package soc.common.views.widgetsInterface.main;

import soc.common.game.player.GamePlayer;
import soc.common.views.meta.ClientBehaviourFactory;
import soc.common.views.widgetsInterface.actions.ActionDetailWidgetFactory;
import soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import soc.common.views.widgetsInterface.actions.DiceWidgetFactory;
import soc.common.views.widgetsInterface.payerInfo.StockItemWidgetFactory;

public interface ClientFactory
{
    public ActionWidgetFactory getActionWidgetFactory(GamePlayer player);

    public ClientBehaviourFactory getBehaviourFactory();

    public GameWidgetFactory getGameWidgetFactory();

    public ActionDetailWidgetFactory getActionDetailWidgetFactory();

    public GamePhaseStatusWidgetFactory getGamePhaseStatusWidgetFactory();

    public StockItemWidgetFactory getStockItemWidgetFactory(GamePlayer player);

    public DiceWidgetFactory getDiceWidgetFactory(GamePlayer player);
}
