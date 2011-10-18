package org.soc.gwt.client.game;

import org.soc.common.presenters.DebugPresenter.DebugView;
import org.soc.common.views.widgetsInterface.main.*;

import com.google.gwt.inject.client.*;
import com.google.web.bindery.event.shared.*;

public interface GamePresenterGinjector extends Ginjector {
  EventBus eventBus();
  GameWidget gameWidget();
  DebugView debugView();
}
