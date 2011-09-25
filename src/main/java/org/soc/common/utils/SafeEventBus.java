package org.soc.common.utils;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

/** Wraps a SimpleEventBus, and only fires events after handlers have registered. Calling fireEvent
 * is safe for NPE's. */
public class SafeEventBus extends EventBus {
  private SimpleEventBus eventBus;

  @Override public <H extends EventHandler> HandlerRegistration addHandler(Type<H> type, H handler) {
    if (eventBus == null)
      eventBus = new SimpleEventBus();
    return eventBus.addHandler(type, handler);
  }
  @Override public <H extends EventHandler> HandlerRegistration addHandlerToSource(Type<H> type,
          Object source, H handler) {
    if (eventBus == null)
      eventBus = new SimpleEventBus();
    return eventBus.addHandlerToSource(type, source, handler);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    if (eventBus != null)
      eventBus.fireEvent(event);
  }
  @Override public void fireEventFromSource(GwtEvent<?> event, Object source) {
    if (eventBus != null)
      eventBus.fireEventFromSource(event, source);
  }
}
