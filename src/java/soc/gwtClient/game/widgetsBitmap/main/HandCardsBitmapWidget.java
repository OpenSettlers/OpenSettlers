package soc.gwtClient.game.widgetsBitmap.main;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.main.AbstractHandCardsWidget;
import soc.gwtClient.game.widgetsBitmap.generic.ResourceBitmapWidget;
import soc.gwtClient.game.widgetsInterface.generic.ResourceWidget;

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
