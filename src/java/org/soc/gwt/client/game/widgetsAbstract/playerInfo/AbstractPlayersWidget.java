package org.soc.gwt.client.game.widgetsAbstract.playerInfo;

import java.util.HashMap;

import org.soc.common.game.player.GamePlayer;
import org.soc.common.game.player.OrderChangedEvent;
import org.soc.common.game.player.OrderChangedEventHandler;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.payerInfo.PlayerInfoWidget;
import org.soc.common.views.widgetsInterface.payerInfo.PlayersInfoWidget;


import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPlayersWidget implements PlayersInfoWidget,
        OrderChangedEventHandler
{
    protected ComplexPanel rootPanel;
    protected GameWidget gameWidget;
    protected GamePlayer player;
    protected HashMap<GamePlayer, PlayerInfoWidget> playersWidgets = new HashMap<GamePlayer, PlayerInfoWidget>();

    public AbstractPlayersWidget(GameWidget gameWidget)
    {
        this.gameWidget = gameWidget;

        rootPanel = createRootPanel();

        for (GamePlayer player : gameWidget.getGame().getPlayers())
        {
            PlayerInfoWidget widget = createPlayerWidget(gameWidget, player);
            rootPanel.add(widget);
            playersWidgets.put(player, widget);
        }

        gameWidget.getGame().getPlayers().addOrderChangedEventHandler(this);
        rootPanel.setWidth("100%");
    }

    @Override
    public ComplexPanel createRootPanel()
    {
        return new VerticalPanel();
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void onOrderChanged(OrderChangedEvent event)
    {
        rootPanel.clear();

        for (GamePlayer player : gameWidget.getGame().getPlayers())
            rootPanel.add(playersWidgets.get(player));
    }
}
