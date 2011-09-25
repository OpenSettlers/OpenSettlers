package org.soc.gwt.client.game;

import org.soc.common.presenters.DebugPresenter.DebugView;
import org.soc.common.views.widgetsInterface.main.GameWidget;

import com.google.gwt.inject.client.Ginjector;

public interface GamePresenterGinjector extends Ginjector {
  GameWidget gameWidget();
  DebugView debugView();
}
