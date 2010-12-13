package soc.gwtClient.game.widgets.standard.bitmap;


import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractHandCardsWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BitmapHandCardsWidget extends AbstractHandCardsWidget
{
    public BitmapHandCardsWidget(Player player)
    {
        super(player);
        
        for (Resource resource : player.getResources())
        {
            rootPanel.add(new BitmapResourceWidget(resource));
        }
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    protected ComplexPanel createRootPanel()
    {
        HorizontalPanel result = new HorizontalPanel();
        //TODO: set alignment
        return new FlowPanel();
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        if (resourcesChanged.getAddedResources() !=null)
        {
            for (Resource resource : resourcesChanged.getAddedResources())
            {
                rootPanel.add(new BitmapResourceWidget(resource));
            }
        }
        if (resourcesChanged.getRemovedResources() !=null)
        {
            //TODO: Remove widgets
        }
    }

}
