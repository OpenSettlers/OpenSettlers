package soc.gwtClient.game.abstractWidgets;

import java.util.HashMap;

import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.common.game.player.OrderChangedEvent;
import soc.common.game.player.OrderChangedEventHandler;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPlayersWidget implements PlayersWidget,
        OrderChangedEventHandler
{
    protected ComplexPanel rootPanel;
    protected Game game;
    protected GamePlayer player;
    protected HashMap<GamePlayer, PlayerWidget> playersWidgets = new HashMap<GamePlayer, PlayerWidget>();

    public AbstractPlayersWidget(Game game)
    {
        this.game = game;

        rootPanel = createRootPanel();

        for (GamePlayer player : game.getPlayers())
        {
            PlayerWidget widget = createPlayerWidget(game, player);
            rootPanel.add(widget);
            playersWidgets.put(player, widget);
        }

        game.getPlayers().addOrderChangedEventHandler(this);
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

        for (GamePlayer player : game.getPlayers())
        {
            rootPanel.add(playersWidgets.get(player));
        }
    }
}
