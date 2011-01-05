package soc.gwtClient.game.abstractWidgets;

import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.common.game.player.OrderChangedEventHandler;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPlayersWidget implements IPlayersWidget,
        OrderChangedEventHandler
{
    protected ComplexPanel rootPanel;
    protected Game game;
    protected GamePlayer player;

    public AbstractPlayersWidget(Game game)
    {
        this.game = game;

        rootPanel = createRootPanel();

        for (GamePlayer player : game.getPlayers())
        {
            rootPanel.add(createPlayerWidget(game, player));
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

}
