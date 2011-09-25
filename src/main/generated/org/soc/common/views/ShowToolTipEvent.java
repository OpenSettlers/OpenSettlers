package org.soc.common.views;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class ShowToolTipEvent extends GwtEvent<ShowToolTipEvent.ShowToolTipHandler> { 

  public interface HasShowToolTipHandlers extends HasHandlers {
    HandlerRegistration addShowToolTipHandler(ShowToolTipHandler handler);
  }

  public interface ShowToolTipHandler extends EventHandler {
    public void onShowToolTip(ShowToolTipEvent event);
  }

  private static final Type<ShowToolTipHandler> TYPE = new Type<ShowToolTipHandler>();

  public static void fire(HasHandlers source, org.soc.common.views.HasToolTip.ToolTipView toolTipView) {
    source.fireEvent(new ShowToolTipEvent(toolTipView));
  }

  public static Type<ShowToolTipHandler> getType() {
    return TYPE;
  }

  org.soc.common.views.HasToolTip.ToolTipView toolTipView;

  public ShowToolTipEvent(org.soc.common.views.HasToolTip.ToolTipView toolTipView) {
    this.toolTipView = toolTipView;
  }

  protected ShowToolTipEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<ShowToolTipHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.views.HasToolTip.ToolTipView getToolTipView() {
    return toolTipView;
  }

  @Override
  protected void dispatch(ShowToolTipHandler handler) {
    handler.onShowToolTip(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    ShowToolTipEvent other = (ShowToolTipEvent) obj;
    if (toolTipView == null) {
      if (other.toolTipView != null)
        return false;
    } else if (!toolTipView.equals(other.toolTipView))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (toolTipView == null ? 1 : toolTipView.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "ShowToolTipEvent["
                 + toolTipView
    + "]";
  }
}
