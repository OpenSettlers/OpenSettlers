package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class ActionPerformedEvent extends GwtEvent<ActionPerformedEvent.ActionPerformedHandler> { 

  public interface HasActionPerformedHandlers extends HasHandlers {
    HandlerRegistration addActionPerformedHandler(ActionPerformedHandler handler);
  }

  public interface ActionPerformedHandler extends EventHandler {
    public void onActionPerformed(ActionPerformedEvent event);
  }

  private static final Type<ActionPerformedHandler> TYPE = new Type<ActionPerformedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.actions.GameAction performedAction) {
    source.fireEvent(new ActionPerformedEvent(performedAction));
  }

  public static Type<ActionPerformedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.actions.GameAction performedAction;

  public ActionPerformedEvent(org.soc.common.game.actions.GameAction performedAction) {
    this.performedAction = performedAction;
  }

  protected ActionPerformedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<ActionPerformedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.actions.GameAction getPerformedAction() {
    return performedAction;
  }

  @Override
  protected void dispatch(ActionPerformedHandler handler) {
    handler.onActionPerformed(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    ActionPerformedEvent other = (ActionPerformedEvent) obj;
    if (performedAction == null) {
      if (other.performedAction != null)
        return false;
    } else if (!performedAction.equals(other.performedAction))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (performedAction == null ? 1 : performedAction.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "ActionPerformedEvent["
                 + performedAction
    + "]";
  }
}
