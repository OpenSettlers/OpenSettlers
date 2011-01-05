package soc.gwtClient.game.abstractWidgets;

import soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractLongestRoadWidget implements ILongestRoadWidget
{
    protected ComplexPanel rootPanel;
    protected GamePlayer player;
    
    public AbstractLongestRoadWidget(GamePlayer player)
    {
        this.player=player;
        rootPanel = createRootPanel();
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
