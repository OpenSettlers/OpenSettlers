package soc.gwtClient.editor;

import soc.common.board.ports.ClayPort;
import soc.common.board.ports.OrePort;
import soc.common.board.ports.Port;
import soc.common.board.ports.PortList;
import soc.common.board.ports.RandomPort;
import soc.common.board.ports.SheepPort;
import soc.common.board.ports.ThreeToOnePort;
import soc.common.board.ports.TimberPort;
import soc.common.board.ports.WheatPort;
import soc.common.views.behaviour.board.SetPortBehaviour;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PortPanel extends VerticalPanel
{
    private SetPortBehaviour behaviour;
    private static PortList ports = new PortList();

    static
    {
        ports.add(new ThreeToOnePort());

        ports.add(new WheatPort());
        ports.add(new TimberPort());
        ports.add(new OrePort());
        ports.add(new SheepPort());
        ports.add(new ClayPort());

        ports.add(new RandomPort());
    }

    private EventBus eventBus = new SimpleEventBus();

    public HandlerRegistration addBehaviourChangedEventHandler(
                    BehaviourChangedHandler handler)
    {
        return eventBus.addHandler(BehaviourChanged.TYPE, handler);
    }

    public PortPanel(SetPortBehaviour b)
    {
        super();

        behaviour = b;

        for (Port port : ports)
            add(new PortButton(port));
    }

    private class PortButton extends PushButton
    {
        public PortButton(final Port port)
        {
            super(new Image(Resources.mediumIcon(port)));

            addClickHandler(new ClickHandler()
            {
                @Override
                public void onClick(ClickEvent event)
                {
                    behaviour.setPort(port);
                    fireEvent(new BehaviourChanged(behaviour));
                }
            });
        }
    }
}
