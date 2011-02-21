package soc.common.ui;

import soc.common.game.player.GamePlayer;
import soc.common.ui.meta.ClientBehaviourFactory;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.GameWidgetFactory;

public interface ClientFactory
{
    public ActionWidgetFactory getActionWidgetFactory(GamePlayer player);

    public ClientBehaviourFactory getBehaviourFactory();

    public GameWidgetFactory getGameWidgetFactory();

    public ActionDetailWidgetFactory getActionDetailWidgetFactory();
}
