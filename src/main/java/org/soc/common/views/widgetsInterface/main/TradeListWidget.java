package org.soc.common.views.widgetsInterface.main;

import org.soc.common.game.Resources.MutableResourceList;

import com.google.gwt.user.client.ui.*;

public interface TradeListWidget extends IsWidget
{
  public MutableResourceList getWantResources();
  public MutableResourceList getGiveResources();
}
