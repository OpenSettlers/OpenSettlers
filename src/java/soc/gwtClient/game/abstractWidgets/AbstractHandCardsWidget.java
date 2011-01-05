package soc.gwtClient.game.abstractWidgets;

import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractHandCardsWidget implements HandCardsWidget
{
    protected ComplexPanel rootPanel;
    protected abstract ComplexPanel createRootPanel();
    protected GamePlayer player;

    public AbstractHandCardsWidget(GamePlayer player)
    {
        super();
        this.player = player;
        
        rootPanel = createRootPanel();
    }
}
