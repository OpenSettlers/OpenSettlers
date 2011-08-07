package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.board.resources.Resource;
import org.soc.common.board.resources.ResourcesChangedEvent;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.generic.ResourceWidget;
import org.soc.gwt.client.game.widgetsAbstract.main.AbstractHandCardsWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.ResourceBitmapWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class HandCardsBitmapWidget extends AbstractHandCardsWidget
{
    public HandCardsBitmapWidget(GamePlayer player)
    {
        super(player);

        for (Resource resource : player.getResources())
        {
            addWidget(resource);
        }

        rootPanel.setWidth("5em");

        player.getResources().addResourcesChangedEventHandler(this);
    }

    private void addWidget(Resource resource)
    {
        ResourceWidget widget = new ResourceBitmapWidget(resource);
        rootPanel.add(widget);
        resourcesWidgets.put(resource, widget);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    protected ComplexPanel createRootPanel()
    {
        return new VerticalPanel();
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        if (resourcesChanged.getAddedResources() != null)
        {
            for (Resource resource : resourcesChanged.getAddedResources())
            {
                addWidget(resource);
            }
        }
        if (resourcesChanged.getRemovedResources() != null)
        {
            for (Resource resource : resourcesChanged.getRemovedResources())
            {
                for (int i = 0; i < rootPanel.getWidgetCount(); i++)
                {
                    ResourceWidget widget = (ResourceWidget) rootPanel
                            .getWidget(i);
                    if (widget.getResource().equals(resource))
                    {
                        rootPanel.remove(widget);
                        break;
                    }
                }
            }
        }
    }

}
