package org.soc.common.views.widgetsInterface.main;

import org.soc.common.views.widgetsInterface.main.GameWidget.GameWidgetFactory;

import com.google.gwt.user.client.ui.Panel;

public interface GamePanelLayoutWidget
{
  public GameWidgetFactory getGameWidgetFactory();
  public void initialize();
  public Panel getRootPanel();
}
