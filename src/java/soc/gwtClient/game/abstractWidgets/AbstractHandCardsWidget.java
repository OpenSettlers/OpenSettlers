package soc.gwtClient.game.abstractWidgets;

import java.util.HashMap;

import soc.common.board.resources.Resource;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceWidget;

import com.google.gwt.user.client.ui.ComplexPanel;

public abstract class AbstractHandCardsWidget implements HandCardsWidget
{
    protected ComplexPanel rootPanel;

    protected abstract ComplexPanel createRootPanel();

    protected GamePlayer player;
    protected HashMap<Resource, ResourceWidget> resourcesWidgets = new HashMap<Resource, ResourceWidget>();

    public AbstractHandCardsWidget(GamePlayer player)
    {
        super();
        this.player = player;

        rootPanel = createRootPanel();
    }
}
