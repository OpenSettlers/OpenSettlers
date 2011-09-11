package org.soc.gwt.client.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import org.soc.common.board.PortListChangedEvent;
import org.soc.common.board.PortListChangedEvent.PortListChangedHandler;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.Port;
import org.soc.common.game.Port.FourToOnePort;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.toolTips.AbstractPlayerInfoToolTip;

import com.google.gwt.user.client.ui.Image;

public class PortListToolTip extends AbstractPlayerInfoToolTip implements
        PortListChangedHandler
{
  private Map<Port, Image> portImages = new HashMap<Port, Image>();

  public PortListToolTip(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    for (Port port : player.ports())
    {
      if (!(port instanceof FourToOnePort))
      {
        Image portImage = new Image(port.icon().iconDefault());
        portImages.put(port, portImage);
        rootPanel.add(portImage);
      }
    }
    player.ports().addPortListChangedHandler(this);
  }
  @Override public void onPortListChanged(PortListChangedEvent event)
  {
    if (event.getAddedPort() != null
            && !(event.getAddedPort() instanceof FourToOnePort))
    {
      Image portImage = new Image(event.getAddedPort().icon()
              .iconDefault());
      portImages.put(event.getAddedPort(), portImage);
      rootPanel.add(portImage);
    }
    if (event.getRemovedPort() != null)
    {
      Image portImage = portImages.get(event.getRemovedPort());
      rootPanel.remove(portImage);
    }
  }
}
