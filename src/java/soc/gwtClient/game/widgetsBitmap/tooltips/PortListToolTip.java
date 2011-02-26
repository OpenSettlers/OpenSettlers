package soc.gwtClient.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import soc.common.board.ports.FourToOnePort;
import soc.common.board.ports.Port;
import soc.common.board.ports.PortListChangedEvent;
import soc.common.board.ports.PortListChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsAbstract.toolTips.AbstractPlayerInfoToolTip;

import com.google.gwt.user.client.ui.Image;

public class PortListToolTip extends AbstractPlayerInfoToolTip implements
        PortListChangedEventHandler
{
    private Map<Port, Image> portImages = new HashMap<Port, Image>();

    public PortListToolTip(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);

        for (Port port : player.getPorts())
        {
            if (!(port instanceof FourToOnePort))
            {
                Image portImage = new Image(port.getMeta().icon().iconDefault());
                portImages.put(port, portImage);
                rootPanel.add(portImage);
            }
        }

        player.getPorts().addPortListChangedEventHandler(this);
    }

    @Override
    public void onPortsChanged(PortListChangedEvent event)
    {
        if (event.getAddedPort() != null
                && !(event.getAddedPort() instanceof FourToOnePort))
        {
            Image portImage = new Image(event.getAddedPort().getMeta().icon()
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
