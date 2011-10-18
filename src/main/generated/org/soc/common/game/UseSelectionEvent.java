package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class UseSelectionEvent extends GwtEvent<UseSelectionEvent.UseSelectionHandler> { 

  public interface HasUseSelectionHandlers extends HasHandlers {
    HandlerRegistration addUseSelectionHandler(UseSelectionHandler handler);
  }

  public interface UseSelectionHandler extends EventHandler {
    public void onUseSelection(UseSelectionEvent event);
  }

  private static final Type<UseSelectionHandler> TYPE = new Type<UseSelectionHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Resources.ResourceList picked) {
    source.fireEvent(new UseSelectionEvent(picked));
  }

  public static Type<UseSelectionHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Resources.ResourceList picked;

  public UseSelectionEvent(org.soc.common.game.Resources.ResourceList picked) {
    this.picked = picked;
  }

  protected UseSelectionEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<UseSelectionHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Resources.ResourceList getPicked() {
    return picked;
  }

  @Override
  protected void dispatch(UseSelectionHandler handler) {
    handler.onUseSelection(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    UseSelectionEvent other = (UseSelectionEvent) obj;
    if (picked == null) {
      if (other.picked != null)
        return false;
    } else if (!picked.equals(other.picked))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (picked == null ? 1 : picked.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "UseSelectionEvent["
                 + picked
    + "]";
  }
}
