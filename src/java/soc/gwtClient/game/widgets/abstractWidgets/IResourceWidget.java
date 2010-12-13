package soc.gwtClient.game.widgets.abstractWidgets;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;

import soc.common.board.resources.Resource;

public interface IResourceWidget extends IsWidget 
{
    public Resource getResource();
    public HandlerRegistration addClickHandler(ClickHandler handler);
}
