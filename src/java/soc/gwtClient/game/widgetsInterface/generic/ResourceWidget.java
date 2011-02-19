package soc.gwtClient.game.widgetsInterface.generic;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;

import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;

public interface ResourceWidget extends IsWidget 
{
    public Resource getResource();
    public HandlerRegistration addClickHandler(ResourceClickedEventHandler handler);
    public ResourceWidget setEnabled(boolean enabled);
}
