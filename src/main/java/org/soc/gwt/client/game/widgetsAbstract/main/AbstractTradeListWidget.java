package org.soc.gwt.client.game.widgetsAbstract.main;

import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.MutableResourceListImpl;
import org.soc.common.views.widgetsInterface.generic.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsBitmap.generic.*;

import com.google.gwt.user.client.ui.*;

public abstract class AbstractTradeListWidget implements TradeListWidget
{
  private HorizontalPanel rootPanel = new HorizontalPanel();
  private ResourceListWidget wantResourceList;
  private ResourceListWidget giveResourceList;
  private MutableResourceList wantResources = new MutableResourceListImpl();
  private MutableResourceList giveResources = new MutableResourceListImpl();

  public AbstractTradeListWidget()
  {
    wantResourceList = new ResourceListBitmapWidget(wantResources, null,
            null);
    wantResourceList.setHeight("3em");
    giveResourceList = new ResourceListBitmapWidget(giveResources, null,
            null);
    giveResourceList.setHeight("3em");
    rootPanel.add(wantResourceList);
    rootPanel.add(new Label(" for "));
    rootPanel.add(giveResourceList);
    rootPanel.setWidth("200px");
    rootPanel.setHeight("3em");
  }
  /** @return the wantResources */
  public MutableResourceList getWantResources()
  {
    return wantResources;
  }
  /** @return the giveResources */
  public MutableResourceList getGiveResources()
  {
    return giveResources;
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
}
