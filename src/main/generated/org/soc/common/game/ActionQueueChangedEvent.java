package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class ActionQueueChangedEvent extends GwtEvent<ActionQueueChangedEvent.ActionQueueChangedHandler> { 

  public interface HasActionQueueChangedHandlers extends HasHandlers {
    HandlerRegistration addActionQueueChangedHandler(ActionQueueChangedHandler handler);
  }

  public interface ActionQueueChangedHandler extends EventHandler {
    public void onActionQueueChanged(ActionQueueChangedEvent event);
  }

  private static final Type<ActionQueueChangedHandler> TYPE = new Type<ActionQueueChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.actions.GameAction dequeuedAction, org.soc.common.game.actions.GameAction enqueuedAction) {
    source.fireEvent(new ActionQueueChangedEvent(dequeuedAction, enqueuedAction));
  }

  public static Type<ActionQueueChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.actions.GameAction dequeuedAction;
  org.soc.common.game.actions.GameAction enqueuedAction;

  public ActionQueueChangedEvent(org.soc.common.game.actions.GameAction dequeuedAction, org.soc.common.game.actions.GameAction enqueuedAction) {
    this.dequeuedAction = dequeuedAction;
    this.enqueuedAction = enqueuedAction;
  }

  protected ActionQueueChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<ActionQueueChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.actions.GameAction getDequeuedAction() {
    return dequeuedAction;
  }

  public org.soc.common.game.actions.GameAction getEnqueuedAction() {
    return enqueuedAction;
  }

  @Override
  protected void dispatch(ActionQueueChangedHandler handler) {
    handler.onActionQueueChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    ActionQueueChangedEvent other = (ActionQueueChangedEvent) obj;
    if (dequeuedAction == null) {
      if (other.dequeuedAction != null)
        return false;
    } else if (!dequeuedAction.equals(other.dequeuedAction))
      return false;
    if (enqueuedAction == null) {
      if (other.enqueuedAction != null)
        return false;
    } else if (!enqueuedAction.equals(other.enqueuedAction))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (dequeuedAction == null ? 1 : dequeuedAction.hashCode());
    hashCode = (hashCode * 37) + (enqueuedAction == null ? 1 : enqueuedAction.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "ActionQueueChangedEvent["
                 + dequeuedAction
                 + ","
                 + enqueuedAction
    + "]";
  }
}
