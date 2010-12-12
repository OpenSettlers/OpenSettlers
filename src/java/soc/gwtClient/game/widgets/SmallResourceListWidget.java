package soc.gwtClient.game.widgets;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.gwtClient.game.widgets.abstractWidgets.AbstractResourceListWidget;
import soc.gwtClient.game.widgets.abstractWidgets.IResourceListWidget;
import soc.gwtClient.game.widgets.abstractWidgets.IResourceWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class SmallResourceListWidget extends AbstractResourceListWidget
{
    public SmallResourceListWidget()
    {
        super();
        
        rootPanel = new HorizontalPanel();
        maximumResources = 0;
    }

    @Override
    public IResourceWidget createResourceWidget(Resource resource)
    {
        return new SmallResourceWidget(resource);
    }
}
