package org.soc.gwt.client.game;

import java.util.HashMap;
import java.util.Map;

import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.meta.ClientBehaviourFactory;
import org.soc.common.views.meta.DefaultGameActionBehaviourFactory;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.actions.DiceWidgetFactory;
import org.soc.common.views.widgetsInterface.main.ClientFactory;
import org.soc.common.views.widgetsInterface.main.GamePhaseStatusWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.GameWidgetFactory;
import org.soc.common.views.widgetsInterface.payerInfo.StockItemWidgetFactory;
import org.soc.gwt.client.game.widgetsBitmap.actionDetail.ActionDetailBitmapWidgetFactory;
import org.soc.gwt.client.game.widgetsBitmap.actions.ActionWidgetBitmapFactory;
import org.soc.gwt.client.game.widgetsBitmap.actions.DiceWidgetBitmapFactory;
import org.soc.gwt.client.game.widgetsBitmap.main.GameBitmapWidgetFactory;
import org.soc.gwt.client.game.widgetsBitmap.main.GamePhaseStatusBitmapWidgetFactory;
import org.soc.gwt.client.game.widgetsBitmap.playerInfo.StockItemBitmapWidgetFactory;


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
        gamePhaseStausWidgetFactory = new GamePhaseStatusBitmapWidgetFactory(
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
        return gamePhaseStausWidgetFactory;
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