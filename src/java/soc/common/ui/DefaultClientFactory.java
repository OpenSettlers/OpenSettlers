package soc.common.ui;

import java.util.HashMap;
import java.util.Map;

import soc.common.game.player.GamePlayer;
import soc.common.ui.meta.ClientBehaviourFactory;
import soc.common.ui.meta.DefaultGameActionBehaviourFactory;
import soc.gwtClient.game.widgetsBitmap.actions.ActionWidgetBitmapFactory;
import soc.gwtClient.game.widgetsBitmap.main.GameBitmapWidgetFactory;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidgetFactory;

public class DefaultClientFactory implements ClientFactory
{
    private ClientBehaviourFactory clientBehaviourFactory;
    private GameWidget gameWidget;
    private GameWidgetFactory gameWidgetFactory;
    private Map<GamePlayer, ActionWidgetFactory> actionWidgetFactories = new HashMap<GamePlayer, ActionWidgetFactory>();

    public DefaultClientFactory(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;

        clientBehaviourFactory = new DefaultGameActionBehaviourFactory(
                gameWidget);
        gameWidgetFactory = new GameBitmapWidgetFactory(gameWidget);
    }

    @Override
    public ActionWidgetFactory getActionWidgetFactory(GamePlayer player)
    {
        if (actionWidgetFactories.get(player) == null)
            actionWidgetFactories.put(player, new ActionWidgetBitmapFactory(
                    gameWidget, player));

        return actionWidgetFactories.get(player);
    }

    @Override
    public ClientBehaviourFactory getBehaviourFactory()
    {
        return clientBehaviourFactory;
    }

    @Override
    public GameWidgetFactory getGameWidgetFactory()
    {
        return gameWidgetFactory;
    }

}
