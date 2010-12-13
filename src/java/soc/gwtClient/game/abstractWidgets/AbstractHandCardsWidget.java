package soc.gwtClient.game.abstractWidgets;

import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.game.Player;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractHandCardsWidget implements IHandCardsWidget
{
    protected ComplexPanel rootPanel;
    protected abstract ComplexPanel createRootPanel();
    protected Player player;

    public AbstractHandCardsWidget(Player player)
    {
        super();
        this.player = player;
        
        rootPanel = createRootPanel();
    }
}
