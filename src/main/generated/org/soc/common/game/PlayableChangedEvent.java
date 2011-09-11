package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class PlayableChangedEvent extends GwtEvent<PlayableChangedEvent.PlayableChangedHandler> { 

  public interface HasPlayableChangedHandlers extends HasHandlers {
    HandlerRegistration addPlayableChangedHandler(PlayableChangedHandler handler);
  }

  public interface PlayableChangedHandler extends EventHandler {
    public void onPlayableChanged(PlayableChangedEvent event);
  }

  private static final Type<PlayableChangedHandler> TYPE = new Type<PlayableChangedHandler>();

  public static void fire(HasHandlers source, boolean playable) {
    source.fireEvent(new PlayableChangedEvent(playable));
  }

  public static Type<PlayableChangedHandler> getType() {
    return TYPE;
  }

  boolean playable;

  public PlayableChangedEvent(boolean playable) {
    this.playable = playable;
  }

  protected PlayableChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<PlayableChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public boolean isPlayable() {
    return playable;
  }

  @Override
  protected void dispatch(PlayableChangedHandler handler) {
    handler.onPlayableChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    PlayableChangedEvent other = (PlayableChangedEvent) obj;
    if (playable != other.playable)
        return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + new Boolean(playable).hashCode();
    return hashCode;
  }

  @Override
  public String toString() {
    return "PlayableChangedEvent["
                 + playable
    + "]";
  }
}
