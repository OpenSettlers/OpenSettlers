package org.soc.common.views.widgetsInterface.generic;

import org.soc.common.board.resources.AbstractResource;
import org.soc.common.board.resources.Resource;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;


public interface ResourceWidget extends IsWidget 
{
    public Resource getResource();
    public HandlerRegistration addClickHandler(ResourceClickedEventHandler handler);
    public ResourceWidget setEnabled(boolean enabled);
}
