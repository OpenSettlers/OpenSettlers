package org.soc.gwt.client.game.widgetsBitmap.tooltips;

import java.util.*;

import org.soc.common.core.GenericList.Adds.Added;
import org.soc.common.core.GenericList.Models;
import org.soc.common.core.GenericList.Removes.Removed;
import org.soc.common.game.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.toolTips.*;

import com.google.gwt.user.client.ui.*;

public class PortListToolTip extends AbstractPlayerInfoToolTip
{
  private Map<Port, Image> portImages = new HashMap<Port, Image>();

  public PortListToolTip(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    for (Port port : player.ports())
    {
      Image portImage = new Image(Models.icon(port).iconDefault());
      portImages.put(port, portImage);
      rootPanel.add(portImage);
    }
    player.ports().addAddedHandler(new Added<Port>() {
      @Override public void added(Port port) {
        Image portImage = new Image(Models.icon(port).iconDefault());
        portImages.put(port, portImage);
        rootPanel.add(portImage);
      }
    });
    player.ports().addRemovedHandler(new Removed<Port>() {
      @Override public void removed(Port port) {
        Image portImage = portImages.get(port);
        rootPanel.remove(portImage);
      }
    });
  }
}
