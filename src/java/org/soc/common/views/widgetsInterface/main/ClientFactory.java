package org.soc.common.views.widgetsInterface.main;

import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.meta.ClientBehaviourFactory;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.actions.DiceWidgetFactory;
import org.soc.common.views.widgetsInterface.payerInfo.StockItemWidgetFactory;

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
