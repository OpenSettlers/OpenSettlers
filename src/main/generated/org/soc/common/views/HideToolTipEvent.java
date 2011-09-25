package org.soc.common.views;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class HideToolTipEvent extends GwtEvent<HideToolTipEvent.HideToolTipHandler> { 

  public interface HasHideToolTipHandlers extends HasHandlers {
    HandlerRegistration addHideToolTipHandler(HideToolTipHandler handler);
  }

  public interface HideToolTipHandler extends EventHandler {
    public void onHideToolTip(HideToolTipEvent event);
  }

  private static final Type<HideToolTipHandler> TYPE = new Type<HideToolTipHandler>();

  public static void fire(HasHandlers source, org.soc.common.views.HasToolTip.ToolTipView toolTipView) {
    source.fireEvent(new HideToolTipEvent(toolTipView));
  }

  public static Type<HideToolTipHandler> getType() {
    return TYPE;
  }

  org.soc.common.views.HasToolTip.ToolTipView toolTipView;

  public HideToolTipEvent(org.soc.common.views.HasToolTip.ToolTipView toolTipView) {
    this.toolTipView = toolTipView;
  }

  protected HideToolTipEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<HideToolTipHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.views.HasToolTip.ToolTipView getToolTipView() {
    return toolTipView;
  }

  @Override
  protected void dispatch(HideToolTipHandler handler) {
    handler.onHideToolTip(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    HideToolTipEvent other = (HideToolTipEvent) obj;
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
    return "HideToolTipEvent["
                 + toolTipView
    + "]";
  }
}
