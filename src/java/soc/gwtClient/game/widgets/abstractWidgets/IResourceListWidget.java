package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEventHandler;

import com.google.gwt.user.client.ui.IsWidget;

public interface IResourceListWidget extends IsWidget, ResourcesChangedEventHandler
{
    public int getMaximumResources();
    public IResourceWidget createResourceWidget(Resource resource);
}
