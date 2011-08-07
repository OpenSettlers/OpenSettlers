package org.soc.gwt.client.game.widgetsAbstract.playerInfo;

import org.soc.common.board.resources.ResourcesChangedEvent;
import org.soc.common.board.resources.ResourcesChangedEventHandler;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.payerInfo.ResourceAmountWidget;

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
