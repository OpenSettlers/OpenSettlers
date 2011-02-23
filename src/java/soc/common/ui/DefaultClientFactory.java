package soc.common.ui;

import java.util.HashMap;
import java.util.Map;

import soc.common.game.player.GamePlayer;
import soc.common.ui.meta.ClientBehaviourFactory;
import soc.common.ui.meta.DefaultGameActionBehaviourFactory;
import soc.gwtClient.game.widgetsBitmap.actionDetail.ActionDetailBitmapWidgetFactory;
import soc.gwtClient.game.widgetsBitmap.actions.ActionWidgetBitmapFactory;
import soc.gwtClient.game.widgetsBitmap.actions.DiceWidgetBitmapFactory;
import soc.gwtClient.game.widgetsBitmap.main.GameBitmapWidgetFactory;
import soc.gwtClient.game.widgetsBitmap.playerInfo.StockItemBitmapWidgetFactory;
import soc.gwtClient.game.widgetsInterface.actions.ActionDetailWidgetFactory;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidgetFactory;
import soc.gwtClient.game.widgetsInterface.actions.DiceWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.GamePhaseStatusWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidgetFactory;
import soc.gwtClient.game.widgetsInterface.playerInfo.StockItemWidgetFactory;

public class DefaultClientFactory implements ClientFactory
{
    private ClientBehaviourFactory clientBehaviourFactory;
    private GameWidget gameWidget;
    private GameWidgetFactory gameWidgetFactory;
    private Map<GamePlayer, ActionWidgetFactory> actionWidgetFactories = new HashMap<GamePlayer, ActionWidgetFactory>();
    private ActionDetailWidgetFactory actionDetailWidgetFactory;
    private GamePhaseStatusWidgetFactory gamePhaseStausWidgetFactory;
    private Map<GamePlayer, StockItemWidgetFactory> stockItemWidgetFactories = new HashMap<GamePlayer, StockItemWidgetFactory>();
    private Map<GamePlayer, DiceWidgetFactory> diceWidgetFactories = new HashMap<GamePlayer, DiceWidgetFactory>();

    public DefaultClientFactory(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;

        clientBehaviourFactory = new DefaultGameActionBehaviourFactory(
                gameWidget);
        gameWidgetFactory = new GameBitmapWidgetFactory(gameWidget);
        actionDetailWidgetFactory = new ActionDetailBitmapWidgetFactory(
                gameWidget);
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

    @Override
    public ActionDetailWidgetFactory getActionDetailWidgetFactory()
    {
        return actionDetailWidgetFactory;
    }

    @Override
    public GamePhaseStatusWidgetFactory getGamePhaseStatusWidgetFactory()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StockItemWidgetFactory getStockItemWidgetFactory(GamePlayer player)
    {
        if (stockItemWidgetFactories.get(player) == null)
            stockItemWidgetFactories.put(player,
                    new StockItemBitmapWidgetFactory(gameWidget, player));

        return stockItemWidgetFactories.get(player);
    }

    @Override
    public DiceWidgetFactory getDiceWidgetFactory(GamePlayer player)
    {
        if (diceWidgetFactories.get(player) == null)
            diceWidgetFactories.put(player, new DiceWidgetBitmapFactory(
                    gameWidget, player));

        return diceWidgetFactories.get(player);
    }
}