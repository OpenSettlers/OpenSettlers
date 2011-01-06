package soc.gwtClient.game.abstractWidgets;

import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AbstractResourceAmountWidget implements ResourceAmountWidget, ResourcesChangedEventHandler
{
    protected GamePlayer player;
    protected ComplexPanel rootPanel;

    public AbstractResourceAmountWidget(GamePlayer player)
    {
        this.player=player;
        
        rootPanel = createRootPanel();
        
        player.getResources().addResourcesChangedEventHandler(this);
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
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        
    }
}
