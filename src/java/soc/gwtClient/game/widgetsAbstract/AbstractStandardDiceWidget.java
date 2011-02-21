package soc.gwtClient.game.widgetsAbstract;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsInterface.actions.StandardDiceWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractStandardDiceWidget implements StandardDiceWidget
{
    protected GameWidget gamePanel;
    protected AbsolutePanel rootPanel;
    protected GamePlayer player;
    protected boolean enabled = false;

    protected abstract ComplexPanel createRootPanel();

    public AbstractStandardDiceWidget(GameWidget gamePanel, GamePlayer player)
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