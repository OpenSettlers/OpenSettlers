package soc.common.ui;

import soc.common.game.player.GamePlayer;
import soc.common.ui.meta.ClientBehaviourFactory;
import soc.gwtClient.game.widgetsInterface.actions.ActionDetailWidgetFactory;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidgetFactory;
import soc.gwtClient.game.widgetsInterface.actions.DiceWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.GamePhaseStatusWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.GameWidgetFactory;
import soc.gwtClient.game.widgetsInterface.playerInfo.StockItemWidgetFactory;

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
