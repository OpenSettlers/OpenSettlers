package org.soc.common.views.widgetsInterface.main;

import org.soc.common.game.ResourceList;

import com.google.gwt.user.client.ui.IsWidget;

public interface TradeListWidget extends IsWidget
{
  public ResourceList getWantResources();
  public ResourceList getGiveResources();
}
