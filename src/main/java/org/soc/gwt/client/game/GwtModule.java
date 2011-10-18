package org.soc.gwt.client.game;

import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsBitmap.main.*;

import com.google.gwt.inject.client.*;
import com.google.web.bindery.event.shared.*;

public class GwtModule extends AbstractGinModule {
  @Override protected void configure() {
    bind(DebugWidget.class).to(DebugBitmapPanel.class);
    bind(EventBus.class).to(SimpleEventBus.class);
  }
}
