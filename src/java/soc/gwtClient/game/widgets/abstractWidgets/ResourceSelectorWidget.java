package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.board.resources.Resource;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;

/*
 * Widget representing a resource selector widget. Usually a button
 * which spawns an event when clicked
 */
public interface ResourceSelectorWidget extends IsWidget
{
    public Resource getResource();
    public HandlerRegistration addClickHandler(ResourceClickedEventHandler handler);
    public ResourceSelectorWidget setEnabled(boolean enabled);
}
