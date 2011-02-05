package soc.gwtClient.game.abstractWidgets;

import java.util.HashMap;

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
    protected GamePanel gamePanel;
    protected GamePlayer player;
    protected HashMap<GamePlayer, PlayerWidget> playersWidgets = new HashMap<GamePlayer, PlayerWidget>();

    public AbstractPlayersWidget(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;

        rootPanel = createRootPanel();

        for (GamePlayer player : gamePanel.getGame().getPlayers())
        {
            PlayerWidget widget = createPlayerWidget(gamePanel, player);
            rootPanel.add(widget);
            playersWidgets.put(player, widget);
        }

        gamePanel.getGame().getPlayers().addOrderChangedEventHandler(this);
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

        for (GamePlayer player : gamePanel.getGame().getPlayers())
            rootPanel.add(playersWidgets.get(player));
    }
}
