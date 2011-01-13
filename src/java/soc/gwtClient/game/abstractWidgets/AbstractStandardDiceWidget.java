package soc.gwtClient.game.abstractWidgets;

import soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractStandardDiceWidget implements StandardDiceWidget
{
    protected GamePanel gamePanel;
    protected AbsolutePanel rootPanel;
    protected GamePlayer player;
    protected boolean enabled = false;

    protected abstract ComplexPanel createRootPanel();

    public AbstractStandardDiceWidget(GamePanel gamePanel, GamePlayer player)
    {
        this.gamePanel = gamePanel;
        this.player = player;
        rootPanel = new AbsolutePanel();
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

}
